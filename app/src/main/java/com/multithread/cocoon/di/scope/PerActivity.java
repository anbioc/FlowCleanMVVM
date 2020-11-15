package com.multithread.cocoon.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Dagger activity scope. Used in activity modules to indicate that the scope of the injected object
 * is an activity.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
