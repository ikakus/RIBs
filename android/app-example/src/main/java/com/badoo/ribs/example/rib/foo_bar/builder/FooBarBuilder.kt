package com.badoo.ribs.example.rib.foo_bar.builder

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.Builder
import com.badoo.ribs.customisation.customisationsBranchFor
import com.badoo.ribs.customisation.getOrDefault
import com.badoo.ribs.example.rib.foo_bar.FooBar
import com.badoo.ribs.example.rib.foo_bar.FooBarNode

class FooBarBuilder(
    dependency: FooBar.Dependency
) : Builder<FooBar.Dependency, FooBarNode>(object : FooBar {}) {

    override val dependency : FooBar.Dependency = object : FooBar.Dependency by dependency {
        override fun ribCustomisation() = dependency.customisationsBranchFor(FooBar::class)
    }

    override fun build(buildParams: BuildParams<Nothing?>): FooBarNode =
        DaggerFooBarComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = dependency.getOrDefault(FooBar.Customisation()),
                buildParams = buildParams
            )
            .node()
}
