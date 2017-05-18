package com.github.tothcs;

import com.github.tothcs.interactor.InteractorModule;
import com.github.tothcs.mock.MockNetworkModule;
import com.github.tothcs.repository.TestRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends NotesApplicationComponent {
}