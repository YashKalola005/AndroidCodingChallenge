package com.task.demo.utils

interface DomainMapper <T, DomainModel>{


    fun mapFromDomainModel(domainModel: DomainModel): T
}