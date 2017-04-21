package com.github.tothcs.network.note;

import io.swagger.client.CollectionFormats.*;


import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import io.swagger.client.model.NotesReply;
import io.swagger.client.model.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NoteApi {
  
  /**
   * Get all notes
   * 
   * @return Call<NotesReply>
   */
  
  @GET("note")
  Call<NotesReply> getNotes();
    

  
  /**
   * Add a new note
   * 
   * @param body note object that needs to be stored
   * @return Call<Void>
   */
  
  @POST("note")
  Call<Void> addNote(
          @Body Note body
  );

  
  /**
   * Find note by ID
   * Returns a single note
   * @param noteId ID of note to return
   * @return Call<Note>
   */
  
  @GET("note/{noteId}")
  Call<Note> getNoteById(
          @Path("noteId") Long noteId
  );

  
  /**
   * Update an existing note
   * 
   * @param body note object that needs to be updated
   * @return Call<Void>
   */
  
  @PUT("note/{noteId}")
  Call<Void> updateNote(
          @Body Note body
  );

  
  /**
   * Deletes a note
   * 
   * @param noteId note id to delete
   * @return Call<Void>
   */
  
  @DELETE("note/{noteId}")
  Call<Void> deleteNote(
          @Path("noteId") Long noteId
  );

  
}
