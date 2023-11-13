package com.tommygr.gamequiz.data.local.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.tommygr.gamequiz.data.source.local.GameQuizDb
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import com.tommygr.shared_test.datagenerators.quizElementDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class QuizElementDaoTest {
    private lateinit var database: GameQuizDb
    private lateinit var quizElementDao: QuizElementDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GameQuizDb::class.java
        ).allowMainThreadQueries().build()

        quizElementDao = database.quizElementDao()
    }

    @Test
    fun insertQuizElementsList_retrieveCorrectList() {
        val elementsList = listOf(
            quizElementDataModel("1"),
            quizElementDataModel("2"),
            quizElementDataModel("3"),
            quizElementDataModel("4"),
            quizElementDataModel("5")
        )
        quizElementDao.insertAll(elementsList)

        val retrievedList = quizElementDao.getAllQuizElements()
        assertThat(retrievedList).isEqualTo(elementsList)
    }

    @Test
    fun insertSingleQuizElements_getCorrectSingleValues() {
        val element = quizElementDataModel("1")

        quizElementDao.insertQuizElement(element)

        val retrievedFirstElement = quizElementDao.getQuizElement("1")
        assertThat(retrievedFirstElement).isEqualTo(element)
    }

    @Test
    fun updateQuizElement_ElementChanged() {
        val firstElement = quizElementDataModel("1")
        val updatedElement = firstElement.copy(wasShown = true)

        quizElementDao.insertQuizElement(firstElement)
        quizElementDao.updateQuizElement(updatedElement)

        val retrievedElement = quizElementDao.getQuizElement("1")
        assertThat(retrievedElement).isEqualTo(updatedElement)
    }

    @Test
    fun deleteQuizElement_ElementIsNull() {
        val element = quizElementDataModel("1")

        quizElementDao.insertQuizElement(element)
        quizElementDao.deleteQuizElement(element)

        val retrievedElement = quizElementDao.getQuizElement("1")
        assertThat(retrievedElement).isNull()
    }

    @Test
    fun clearQuizElements_ElementsIsEmpty() {
        val elementList = listOf(
            quizElementDataModel("1"),
            quizElementDataModel("2"),
            quizElementDataModel("3"),
        )

        quizElementDao.insertAll(elementList)
        quizElementDao.clear()

        val retrievedElement = quizElementDao.getAllQuizElements()
        assertThat(retrievedElement).isEmpty()
    }

    @Test
    fun insertQuizElements_retrieveElementsAsFlow() = runTest {
        val elementsList = listOf(
            quizElementDataModel("1"),
            quizElementDataModel("2"),
            quizElementDataModel("3"),
        )
        quizElementDao.insertAll(elementsList)

        quizElementDao.observeAllQuizElements().test {
            val elementsListFlow = awaitItem()
            assertThat(elementsListFlow).isEqualTo(elementsList)
            cancel()
        }
    }

    @Test
    fun updateQuizElements_EmitChangesAsFlow() = runTest {
        val elementsList = listOf(
            quizElementDataModel("1"),
            quizElementDataModel("2"),
            quizElementDataModel("3"),
        )
        val updatedElement = elementsList[0].copy(isSolved = true)
        val updatedList = elementsList.toMutableList()
        updatedList[0] = updatedElement

        quizElementDao.insertAll(elementsList)

        quizElementDao.observeAllQuizElements().test {
            val elementsListFlow = awaitItem()
            assertThat(elementsListFlow).isEqualTo(elementsList)

            launch(Dispatchers.Default) {
                delay(100)
                quizElementDao.updateQuizElement(updatedElement)
            }

            assertThat(awaitItem()).isEqualTo(updatedList)
            cancel()
        }
    }

    @Test
    fun insertSingleQuizElement_GetElementAsFlow() = runTest {
        val element = quizElementDataModel("1")

        quizElementDao.insertQuizElement(element)

        quizElementDao.observeQuizElement("1").test {
            val elementFlow = awaitItem()
            assertThat(elementFlow).isEqualTo(element)
            cancel()
        }
    }

    @Test
    fun updateSingleQuizElement_EmitChangesAsFlow() = runTest {
        val element = quizElementDataModel("1")
        val updatedElement = element.copy(wasShown = true)
        quizElementDao.insertQuizElement(element)

        quizElementDao.observeQuizElement("1").test {
            val elementFlow = awaitItem()
            assertThat(elementFlow).isEqualTo(element)

            launch(Dispatchers.Default) {
                delay(100)
                quizElementDao.updateQuizElement(updatedElement)
            }

            assertThat(awaitItem()).isEqualTo(updatedElement)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}