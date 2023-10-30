import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;


public class Artist {

    private String ID;
    private String Name;
    private String Address;
    private String Birthdate;
    private String Bio;
    private ArrayList<String> Occupations; // like singer, songwriter, composer
    private ArrayList<String> Genres; // like rock, jazz, blues, pop, classical
    private ArrayList<String> Awards;

    public Artist(String id, String name, String address, String birthdate, String bio,
                  ArrayList<String> occupations, ArrayList<String> genres, ArrayList<String> awards) {
        ID = id;
        Name = name;
        Birthdate = birthdate;
        Bio = bio;
        Occupations = occupations;
        Genres = genres;
        Awards = awards;
        Address = address;
    }

    public boolean addArtist() {
        // Condition 1: Artist ID should be exactly 10 characters long
        if (ID.length() != 10) {
            System.out.println("Validation Error: Artist ID should be exactly 10 characters long.");
            return false;
        }

        // Check the first three characters are numbers between 5 to 9
        for (int i = 0; i < 3; i++) {
            char digit = ID.charAt(i);
            if (digit < '5' || digit > '9') {
                System.out.println("Validation Error: Artist ID should have first three characters as numbers between 5 to 9.");
                return false;
            }
        }

        // Check the characters 4th to 8th are uppercase letters from A-Z
        for (int i = 3; i < 8; i++) {
            char letter = ID.charAt(i);
            if (letter < 'A' || letter > 'Z') {
                System.out.println("Validation Error: Artist ID should have characters 4th to 8th as uppercase letters.");
                return false;
            }
        }

        // Check the last two characters are special characters
        for (int i = 8; i < 10; i++) {
            char specialChar = ID.charAt(i);
            if (!(specialChar >= '!' && specialChar <= '/' || specialChar >= ':' && specialChar <= '@'
                    || specialChar >= '[' && specialChar <= '`' || specialChar >= '{' && specialChar <= '~')) {
                System.out.println("Validation Error: Artist ID should have last two characters as special characters.");
                return false;
            }
        }

        // Condition 2: Birthdate validation
        if (!Birthdate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.out.println("Validation Error: Invalid birthdate format. Use DD-MM-YYYY.");
            return false;
        }

        // Condition 3: Address validation
        if (!Address.matches("[a-zA-Z|]+\\|[a-zA-Z|]+\\|[a-zA-Z|]+")) {
            System.out.println("Validation Error: Invalid address format. Use City|State|Country.");
            return false;
        }

        // Condition 4: Bio validation
        int bioWordCount = Bio.split("\\s+").length;
        if (bioWordCount < 10 || bioWordCount > 30) {
            System.out.println("Validation Error: Bio should be between 10 and 30 words.");
            return false;
        }

        // Condition 5: Occupations validation
        if (Occupations.isEmpty() || Occupations.size() > 5) {
            System.out.println("Validation Error: Artist should have at least one occupation and at most five.");
            return false;
        }

     // Condition 6: Awards validation
        if (Awards.size() > 3) {
            System.out.println("Validation Error: Artist can have at most three awards.");
            return false;
        }

        Pattern awardPattern = Pattern.compile("\\d{4},\\s[\\w\\s]+");
        for (String award : Awards) {
            Matcher matcher = awardPattern.matcher(award);
            if (!matcher.matches()) {
                System.out.println("Validation Error: Invalid award format. Use Year, Title.");
                return false;
            }

            // Split the award string into year and title
            String[] awardParts = award.split(", ");
            if (awardParts.length != 2) {
                System.out.println("Validation Error: Invalid award format. Use Year, Title.");
                return false;
            }

            // Check if the title contains 4 to 10 words
            String awardTitle = awardParts[1];
            String[] titleWords = awardTitle.split("\\s+");
            if (titleWords.length < 4 || titleWords.length > 10) {
                System.out.println("Validation Error: Award title should contain between 4 and 10 words.");
                return false;
            }
        }


        // Condition 7: Genres validation
        if (Genres.size() < 2 || Genres.size() > 5 || (Genres.contains("pop") && Genres.contains("rock"))) {
            System.out.println("Validation Error: Artist should be active in at least two music genres and at most five, excluding 'pop' and 'rock' together.");
            return false;
        }

        // All validation conditions passed, so write to TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt", true))) {
            writer.write("ID: " + ID);
            writer.newLine();
            writer.write("Name: " + Name);
            writer.newLine();
            writer.write("Birthdate: " + Birthdate);
            writer.newLine();
            writer.write("Address: " + Address);
            writer.newLine();
            writer.write("Bio: " + Bio);
            writer.newLine();
            writer.write("Occupations: " + String.join(", ", Occupations));
            writer.newLine();
            writer.write("Genres: " + String.join(", ", Genres));
            writer.newLine();
            writer.write("Awards: " + String.join(", ", Awards));
            writer.newLine();
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean updateArtist(String newID, String newName, String newAddress, String newBirthdate,
            String newBio, ArrayList<String> newOccupations, ArrayList<String> newGenres,
            ArrayList<String> newAwards) {

        // Reuse the validation code from addArtist
        Artist newArtist = new Artist(newID, newName, newAddress, newBirthdate, newBio,
                newOccupations, newGenres, newAwards);

        if (!newArtist.addArtist()) {
            // New information does not meet the conditions, return false
            return false;
        }

     // Condition 2: If the artist was born before 2000, the occupation cannot be changed
        int birthYear = Integer.parseInt(newArtist.Birthdate.split("-")[2]);
        if (birthYear < 2000) {
            if (!newArtist.Occupations.equals(Occupations)) {
                return false;
            }
        }


        // Condition 3: Awards validation
        for (String award : Awards) {
            String[] awardParts = award.split(",");
            int awardYear = Integer.parseInt(awardParts[0].trim());
            if (awardYear < 2000) {
                // Awards before 2000 cannot be changed (neither year nor title)
                for (String newAward : newAwards) {
                    String[] newAwardParts = newAward.split(",");
                    int newAwardYear = Integer.parseInt(newAwardParts[0].trim());
                    if (awardParts[1].trim().equals(newAwardParts[1].trim()) && awardYear != newAwardYear) {
                        return false;
                    }
                }
            }
        }

        // All validation conditions passed, so write to TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt", true))) {
            writer.write("ID: " + newArtist.ID);
            writer.newLine();
            writer.write("Name: " + newArtist.Name);
            writer.newLine();
            writer.write("Birthdate: " + newArtist.Birthdate);
            writer.newLine();
            writer.write("Address: " + newArtist.Address);
            writer.newLine();
            writer.write("Bio: " + newArtist.Bio);
            writer.newLine();
            writer.write("Occupations: " + String.join(", ", newArtist.Occupations));
            writer.newLine();
            writer.write("Genres: " + String.join(", ", newArtist.Genres));
            writer.newLine();
            writer.write("Awards: " + String.join(", ", newArtist.Awards));
            writer.newLine();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}