package com.example.springdb.study.jpabook.ch7_advanced_mapping.practice

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Ch7Category : RegisterUpdateBaseEntity() {
    @Id @GeneratedValue
    val id: Long? = null

    var name: String? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "parent_id")
    var parent: Ch7Category? = null

    @OneToMany(mappedBy = "parent")
    var child: MutableSet<Ch7Category> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "item_id", nullable = false)]
    )
    var items: MutableSet<Ch7Item> = mutableSetOf()

    fun addItem(item: Ch7Item) {
        this.items.add(item)
        if (!item.categories.contains(this)) {
            item.categories.add(this)
        }
    }

    fun addChild(child: Ch7Category) {
        this.child.add(child)
        if (child.parent != this) {
            child.parent = this
        }
    }
}
