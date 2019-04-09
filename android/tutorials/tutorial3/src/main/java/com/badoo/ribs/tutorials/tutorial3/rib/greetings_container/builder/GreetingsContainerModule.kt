package com.badoo.ribs.tutorials.tutorial3.rib.greetings_container.builder

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.tutorials.tutorial3.rib.greetings_container.GreetingsContainer
import com.badoo.ribs.tutorials.tutorial3.rib.greetings_container.GreetingsContainerInteractor
import com.badoo.ribs.tutorials.tutorial3.rib.greetings_container.GreetingsContainerRouter
import com.badoo.ribs.tutorials.tutorial3.rib.greetings_container.GreetingsContainerView
import com.badoo.ribs.tutorials.tutorial3.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial3.rib.hello_world.builder.HelloWorldBuilder
import dagger.Provides
import io.reactivex.functions.Consumer

@dagger.Module
internal object GreetingsContainerModule {

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun router(
        // pass component to child rib builders, or remove if there are none
        component: GreetingsContainerComponent
    ): GreetingsContainerRouter =
        GreetingsContainerRouter(
            helloWorldBuilder = HelloWorldBuilder(component)
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun interactor(
        router: GreetingsContainerRouter,
        output: Consumer<GreetingsContainer.Output>
    ): GreetingsContainerInteractor =
        GreetingsContainerInteractor(
            router = router,
            output = output
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun node(
        viewFactory: ViewFactory<GreetingsContainerView>,
        router: GreetingsContainerRouter,
        interactor: GreetingsContainerInteractor
    ) : Node<GreetingsContainerView> = Node(
        identifier = object : GreetingsContainer {},
        viewFactory = viewFactory,
        router = router,
        interactor = interactor
    )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun helloWorldOutputConsumer(
        interactor: GreetingsContainerInteractor
    ) : Consumer<HelloWorld.Output> =
        interactor.helloWorldOutputConsumer
}
