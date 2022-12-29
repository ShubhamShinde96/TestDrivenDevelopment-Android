/*
 * Copyright (C) 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.outsideintddexample.acceptancetests

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * MainCoroutineRule installs a TestCoroutineDispatcher for Disptachers.Main.
 *
 * Since it extends TestCoroutineScope, you can directly launch coroutines on the MainCoroutineRule
 * as a [CoroutineScope]:
 *
 * ```
 * mainCoroutineRule.launch { aTestCoroutine() }
 * ```
 *
 * All coroutines started on [MainCoroutineScopeRule] must complete (including timeouts) before the test
 * finishes, or it will throw an exception.
 *
 * When using MainCoroutineRule you should always invoke runBlockingTest on it to avoid creating two
 * instances of [TestCoroutineDispatcher] or [TestCoroutineScope] in your test:
 *
 * ```
 * @Test
 * fun usingRunBlockingTest() = mainCoroutineRule.runBlockingTest {
 *     aTestCoroutine()
 * }
 * ```
 *
 * You may call [DelayController] methods on [MainCoroutineScopeRule] and they will control the
 * virtual-clock.
 *
 * ```
 * mainCoroutineRule.pauseDispatcher()
 * // do some coroutines
 * mainCoroutineRule.advanceUntilIdle() // run all pending coroutines until the dispatcher is idle
 * ```
 *
 * By default, [MainCoroutineScopeRule] will be in a *resumed* state.
 *
 * @param dispatcher if provided, this [TestCoroutineDispatcher] will be used.
 */
@ExperimentalCoroutinesApi
//class MainCoroutineScopeRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :

// ** UPDATE: 'TestCoroutineDispatcher' is deprecated. The execution order of `TestCoroutineDispatcher` can be confusing,
// and the mechanism of pausing is typically misunderstood. Please use `StandardTestDispatcher` or `UnconfinedTestDispatcher`
// instead.

class MainCoroutineScopeRule(private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) :
    TestWatcher(),
    TestCoroutineScope  by TestCoroutineScope(dispatcher)
//    TestCoroutineScope by TestCoroutineScope(dispatcher) {
    {

        //'TestCoroutineScope(CoroutineContext = ...):
//    TestCoroutineScope' is deprecated. This constructs a `TestCoroutineScope` with a deprecated `CoroutineDispatcher`
//    by default. Please use `createTestCoroutineScope` instead.


    override fun starting(description: Description?) {
        super.starting(description)
        // If your codebase allows the injection of other dispatchers like
        // Dispatchers.Default and Dispatchers.IO, consider injecting all of them here
        // and renaming this class to `CoroutineScopeRule`
        //
        // All injected dispatchers in a test should point to a single instance of
        // TestCoroutineDispatcher.
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
//        advanceUntilIdle()
//        cleanupTestCoroutines() // 'cleanupTestCoroutines(): Unit' is deprecated. Please call `runTest`,
//        // which automatically performs the cleanup, instead of using this function.
        Dispatchers.resetMain()
    }

        // Now we need to include this rule into every "UnitTest" that we write.
}
