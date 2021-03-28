package com.jean.cinemapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.jvm.Throws

@ExperimentalCoroutinesApi
class TestCoroutineRules: TestRule {

    private val mTestCoroutineDispatcher = TestCoroutineDispatcher()
    private val mTestCoroutineScope = TestCoroutineScope(mTestCoroutineDispatcher)

    override fun apply(base: Statement?, description: Description?) = object: Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(mTestCoroutineDispatcher)
            base?.evaluate()
            Dispatchers.resetMain()
            mTestCoroutineScope.cleanupTestCoroutines()
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        mTestCoroutineScope.runBlockingTest { block() }
}