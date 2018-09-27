package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.NotesCommand;
import guru.springframework.recipeapp.model.Notes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private final Long ID = 1L;
    private final String NOTES = "recipe notes";

    private NotesCommandToNotes converter;

    @Before
    public void setUp(){
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testConvert_NullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvert_EmptyObject(){
        assertNotNull(converter.convert(new NotesCommand()));
    }


    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}