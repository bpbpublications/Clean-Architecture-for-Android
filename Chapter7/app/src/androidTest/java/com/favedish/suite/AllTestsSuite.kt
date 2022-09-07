package com.favedish.suite

import com.favedish.test.DishDetailsTest
import com.favedish.test.MainActivityTest
import com.favedish.test.RestaurantDetailsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    DishDetailsTest::class,
    RestaurantDetailsTest::class,
    MainActivityTest::class
)
class AllTestsSuite
