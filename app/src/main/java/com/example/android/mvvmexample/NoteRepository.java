package com.example.android.mvvmexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class NoteRepository {
    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;

    NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();

    }

    void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    //AsyncTask for each method

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private final NoteDao noteDao;

        private DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
