package guru.springframework.recipeapp.converter;

import guru.springframework.recipeapp.command.NotesCommand;
import guru.springframework.recipeapp.model.Notes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private final Long ID = 1L;
    private final String NOTES = "notes";

    private NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testConvert_NullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvert_EmptyObject(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(ID, notesCommand.getId());
        assertEquals(NOTES, notesCommand.getRecipeNotes());
    }
}