import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TestArtist {

    @Test
    public void testValidArtistID() {
        // Create an Artist with a valid ID
        ArrayList<String> occupations = new ArrayList<>();
        occupations.add("singer");
        ArrayList<String> genres = new ArrayList<>();
        genres.add("rock");
        genres.add("kpop");
        ArrayList<String> awards = new ArrayList<>();
        awards.add("2022, Best Song for Visual Media and Beyond");
        Artist artist = new Artist("569MMMRR_%", "John Doe", "Houston|TX|USA", "15-05-1995",
                "This is the bio of the best artist in the world.", occupations, genres, awards);

        // Call the addArtist method and assert that it returns true, indicating success
        boolean added = artist.addArtist();
        assertTrue(added, "Valid Artist ID should be accepted.");
    }

    @Test
    public void testInvalidBio_WordCount() {
        // Create an Artist with an invalid Bio (word count < 10)
        ArrayList<String> occupations2 = new ArrayList<>();
        occupations2.add("songwriter");
        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("pop");
        ArrayList<String> awards2 = new ArrayList<>();
        awards2.add("2023, Best New Artist");
        Artist artist2 = new Artist("569MMMRR_%", "Jane Smith", "LosAngeles|CA|USA", "01-01-2000",
                "Short bio.", occupations2, genres2, awards2);

        // Call the addArtist method and assert that it throws an exception for the second artist
        assertThrows(Exception.class, () -> artist2.addArtist(), "Invalid Bio (word count) should throw an exception for the second artist.");
    }


    @Test
    public void testInvalidArtistID_Length() {
        // Create an Artist with an invalid ID (length < 10 characters)
        ArrayList<String> occupations = new ArrayList<>();
        occupations.add("singer");
        ArrayList<String> genres = new ArrayList<>();
        genres.add("rock");
        genres.add("kpop");
        ArrayList<String> awards = new ArrayList<>();
        awards.add("2022, Best Song for Visual Media and Beyond");
        Artist artist = new Artist("56A", "John Doe", "Houston|TX|USA", "15-05-1995",
                "This is the bio of the best artist in the world.", occupations, genres, awards);

        // Call the addArtist method and expect an exception to be thrown
        assertThrows(Exception.class, () -> artist.addArtist(), "Invalid Artist ID (length) should throw an exception.");
    }

    @Test
    public void testInvalidGenres_Count() {
        // Create an Artist with invalid Genres (count < 2)
        ArrayList<String> occupations = new ArrayList<>();
        occupations.add("singer");
        ArrayList<String> genres = new ArrayList<>();
        genres.add("rock");
        ArrayList<String> awards = new ArrayList<>();
        awards.add("2022, Best Song for Visual Media and Beyond");
        Artist artist = new Artist("569MMMRR_%", "John Doe", "Houston|TX|USA", "15-05-1995",
                "This is the bio of the best artist in the world.", occupations, genres, awards);

        // Call the addArtist method and expect an exception to be thrown
        assertThrows(Exception.class, () -> artist.addArtist(), "Invalid Genres (count) should throw an exception.");
    }
    
    @Test
    public void testInvalidAwardTitle_Count() {
        // Create an Artist with only one Occupation specified
        ArrayList<String> occupations = new ArrayList<>();
        occupations.add("singer");
        ArrayList<String> genres = new ArrayList<>();
        genres.add("rock");
        ArrayList<String> awards = new ArrayList<>();
        awards.add("2023, Best New Artist");
        Artist artist = new Artist("569MMMRR_%", "John Doe", "Houston|TX|USA", "15-05-1995",
        		"This is the bio of the best artist in the world.", occupations, genres, awards);

        // Call the addArtist method and expect an exception to be thrown
        assertThrows(Exception.class, () -> artist.addArtist(), "Invalid Award Titles (count) should throw an exception.");
    }
    
    @Test
    public void testInvalidOccupationsInArtist() {
        // Create an Artist with invalid occupations
        ArrayList<String> invalidOccupations = new ArrayList<>();
        invalidOccupations.add("singer");
        invalidOccupations.add("dancer");
        invalidOccupations.add("clown");
        invalidOccupations.add("poledancer");        
        invalidOccupations.add("bass");
        invalidOccupations.add("artist");
        // Invalid: More than 5 occupations
        ArrayList<String> genres = new ArrayList<>();
        genres.add("rock");
        ArrayList<String> awards = new ArrayList<>();
        awards.add("2022, Best Song for Visual Media and Beyond");

        Artist artist = new Artist("569MMMRR_%", "John Doe", "Houston|TX|USA", "15-05-1995",
                "This is the bio of the artist with invalid occupations.", invalidOccupations, genres, awards);

        // Call the addArtist method and expect an exception to be thrown
        assertThrows(Exception.class, () -> artist.addArtist(), "Invalid Occupations (count) should throw an exception.");
    }
 
}




