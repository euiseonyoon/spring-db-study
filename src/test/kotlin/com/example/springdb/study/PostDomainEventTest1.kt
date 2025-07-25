package com.example.springdb.study

import com.example.springdb.study.springdata.common.repositories.custom.PostRepository
import com.example.springdb.study.springdata.common.repositories.domainevent.PostListenerConfig
import com.example.springdb.study.springdata.common.repositories.domainevent.PostPublishedEvent
import com.example.springdb.study.springdata.common.repositories.models.Post
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(PostListenerConfig::class)
class PostDomainEventTest1 {

    @Autowired
    lateinit var postRepository: PostRepository

    // implementing ApplicationEventPublisher
    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Test
    fun test1() {
        // applicationContext = AnnotationConfigApplicationContext 객체
        val post = Post()
        post.title = "title"
        post.content = "content"

        val event = PostPublishedEvent(post)
        applicationContext.publishEvent(event)
    }
}
