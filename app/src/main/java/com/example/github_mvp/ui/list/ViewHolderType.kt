package com.example.github_mvp.ui.list

enum class ViewHolderType(val id: Int) {
    Header(0x00),
    User(0x01);
    companion object {
        fun from(type: Int): ViewHolderType = values().find { it.id == type } ?: User
    }
}