package com.multithread.cocoon.base

interface LocalMapper<LOCAL, DOMAIN> {
    fun mapToLocal(items: DOMAIN): LOCAL
    fun mapFromLocal(items: LOCAL): DOMAIN
}

interface Mapper<INPUT, OUTPUT> {
    fun map(items: INPUT): OUTPUT
}

interface MapperWithParam<INPUT, OUTPUT, PARAM> {
    fun map(items: INPUT, param: PARAM): OUTPUT
}