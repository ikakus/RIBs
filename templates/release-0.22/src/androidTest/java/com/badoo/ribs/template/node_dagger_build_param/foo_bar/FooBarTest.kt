package com.badoo.ribs.template.node_dagger_build_param.foo_bar

import android.os.Bundle
import com.badoo.common.ribs.RibsRule
import com.badoo.ribs.RibTestActivity
import com.badoo.ribs.core.builder.BuildContext.Companion.root
import com.badoo.ribs.template.node_dagger_build_param.foo_bar.builder.FooBarBuilder
import org.junit.Rule
import org.junit.Test

class FooBarTest {

    @get:Rule
    val ribsRule = RibsRule { activity, savedInstanceState -> buildRib(activity, savedInstanceState) }

    private fun buildRib(ribTestActivity: RibTestActivity, savedInstanceState: Bundle?) =
        FooBarBuilder(object : FooBar.Dependency {}).build(
            buildContext = root(savedInstanceState),
            payload = FooBarBuilder.Params(0)
        )


    @Test
    fun testTextDisplayed() {
        TODO("Write UI tests")
    }
}
