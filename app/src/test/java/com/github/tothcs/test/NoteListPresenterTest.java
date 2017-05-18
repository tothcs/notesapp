package com.github.tothcs.test;

import com.github.tothcs.BuildConfig;
import com.github.tothcs.TestHelper;
import com.github.tothcs.model.Note;
import com.github.tothcs.ui.notelist.NoteListPresenter;
import com.github.tothcs.ui.notelist.NoteListScreen;
import com.github.tothcs.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class NoteListPresenterTest {

    private NoteListPresenter noteListPresenter;

    @Before
    public void setup() throws Exception {
        TestHelper.setTestInjector();
        noteListPresenter = new NoteListPresenter();
    }

    @Test
    public void testGetNoteList() {
        NoteListScreen noteListScreen = mock(NoteListScreen.class);
        noteListPresenter.attachScreen(noteListScreen);

        noteListPresenter.getNotes();

        ArgumentCaptor<List<Note>> noteListCaptor = ArgumentCaptor.forClass((Class<List<Note>>) ((Class)List.class));
        verify(noteListScreen, times(1)).updateNotes(noteListCaptor.capture());

        List<Note> capturedNotes = noteListCaptor.getValue();

        assertEquals(3, capturedNotes.size());
    }

    @Test
    public void testRemoveNote() {
        NoteListScreen noteListScreen = mock(NoteListScreen.class);
        noteListPresenter.attachScreen(noteListScreen);

        noteListPresenter.removeNote(1L);

        ArgumentCaptor<List<Note>> noteListCaptor = ArgumentCaptor.forClass((Class<List<Note>>) ((Class)List.class));
        verify(noteListScreen, times(1)).updateNotes(noteListCaptor.capture());

        List<Note> capturedNotes = noteListCaptor.getValue();

        assertEquals(2, capturedNotes.size());
        assertNull(findNoteById(capturedNotes, 1L));
    }

    private Note findNoteById(List<Note> noteList, Long id) {
        for(Note note : noteList) {
            if (id.equals(note.getId())) {
                return note;
            }
        }
        return null;
    }

    @After
    public void tearDown() {
        noteListPresenter.detachScreen();
    }
}