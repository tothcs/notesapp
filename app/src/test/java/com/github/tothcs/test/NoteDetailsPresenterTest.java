package com.github.tothcs.test;

import com.github.tothcs.BuildConfig;
import com.github.tothcs.TestHelper;
import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;
import com.github.tothcs.ui.notedetails.NoteDetailsPresenter;
import com.github.tothcs.ui.notedetails.NoteDetailsScreen;
import com.github.tothcs.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class NoteDetailsPresenterTest {

    private NoteDetailsPresenter noteDetailsPresenter;

    @Before
    public void setup() throws Exception {
        TestHelper.setTestInjector();
        noteDetailsPresenter = new NoteDetailsPresenter();
    }

    @Test
    public void testGetNote() {
        NoteDetailsScreen noteDetailsScreen = mock(NoteDetailsScreen.class);
        noteDetailsPresenter.attachScreen(noteDetailsScreen);

        noteDetailsPresenter.getNoteById(1L);

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteDetailsScreen, times(1)).showNote(noteCaptor.capture());

        Note note = noteCaptor.getValue();

        Assert.assertEquals(Long.valueOf(1L), note.getId());
        Assert.assertEquals(Priority.NORMAL, note.getPriority());
        Assert.assertEquals(Category.STUDY, note.getCategory());
        Assert.assertTrue(note.getTitle().contains("ZH"));
    }

    @After
    public void tearDown() {
        noteDetailsPresenter.detachScreen();
    }
}