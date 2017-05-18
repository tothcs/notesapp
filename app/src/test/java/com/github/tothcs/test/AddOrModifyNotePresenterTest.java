package com.github.tothcs.test;

import com.github.tothcs.BuildConfig;
import com.github.tothcs.TestHelper;
import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNotePresenter;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNoteScreen;
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
public class AddOrModifyNotePresenterTest {

    private AddOrModifyNotePresenter addOrModifyNotePresenter;

    @Before
    public void setup() throws Exception {
        TestHelper.setTestInjector();
        addOrModifyNotePresenter = new AddOrModifyNotePresenter();
    }

    @Test
    public void testGetNote() {
        AddOrModifyNoteScreen addOrModifyNoteScreen = mock(AddOrModifyNoteScreen.class);
        addOrModifyNotePresenter.attachScreen(addOrModifyNoteScreen);

        addOrModifyNotePresenter.getNoteById(1L);

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);
        verify(addOrModifyNoteScreen, times(1)).showNote(noteCaptor.capture());

        Note note = noteCaptor.getValue();

        Assert.assertEquals(Long.valueOf(1L), note.getId());
        Assert.assertEquals(Priority.NORMAL, note.getPriority());
        Assert.assertEquals(Category.STUDY, note.getCategory());
        Assert.assertTrue(note.getTitle().contains("ZH"));
    }

    @Test
    public void testSaveNewNote() {
        AddOrModifyNoteScreen addOrModifyNoteScreen = mock(AddOrModifyNoteScreen.class);
        addOrModifyNotePresenter.attachScreen(addOrModifyNoteScreen);

        Note note = new Note();
        note.setCategory(Category.STUDY);
        note.setPriority(Priority.HIGH);
        note.setTitle("Teszt title");
        note.setDescription("Teszt description");
        addOrModifyNotePresenter.saveNote(note);

        verify(addOrModifyNoteScreen, times(1)).navigateToNoteList();
    }

    @Test
    public void testUpdateNote() {
        AddOrModifyNoteScreen addOrModifyNoteScreen = mock(AddOrModifyNoteScreen.class);
        addOrModifyNotePresenter.attachScreen(addOrModifyNoteScreen);

        Note note = new Note();
        note.setId(1L);
        note.setCategory(Category.STUDY);
        note.setPriority(Priority.HIGH);
        note.setTitle("Teszt title");
        note.setDescription("Teszt description");
        addOrModifyNotePresenter.saveNote(note);

        verify(addOrModifyNoteScreen, times(1)).navigateToNoteList();

        addOrModifyNotePresenter.getNoteById(1L);

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);
        verify(addOrModifyNoteScreen, times(1)).showNote(noteCaptor.capture());

        Note capturedNote = noteCaptor.getValue();

        Assert.assertEquals(Long.valueOf(1L), note.getId());
        Assert.assertEquals(Priority.HIGH, note.getPriority());
        Assert.assertEquals(Category.STUDY, note.getCategory());
        Assert.assertEquals("Teszt title", note.getTitle());
        Assert.assertEquals("Teszt description", note.getDescription());
    }

    @After
    public void tearDown() {
        addOrModifyNotePresenter.detachScreen();
    }
}