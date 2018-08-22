package com.kotlin.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class PerComponentScope