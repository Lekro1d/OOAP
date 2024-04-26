package com.example.visitor.items

import com.example.visitor.visitors.Visitor

interface Food {
    fun accept(visitor: Visitor)
}