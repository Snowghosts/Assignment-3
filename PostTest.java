import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PostTest {

    private Post validPost;

    @BeforeEach
    public void setUp() {
        String[] tags = {"tag1", "tag2"};
        validPost = new Post(1, "Valid Title", "This is a valid body with more than 250 characters. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada.", tags, "Difficult", "Immediately Needed");
    }

    @Test
    public void testAddPost() {
        assertTrue(validPost.addPost(), "The post should be added successfully.");
    }

    @Test
    public void testValidateTitleLength() {
        assertTrue(validPost.validateTitleLength(), "The title should be valid.");
    }

    @Test
    public void testValidateBodyLength() {
        assertTrue(validPost.validateBodyLength(), "The body should be valid.");
    }

    @Test
    public void testValidateTagsLength() {
        assertTrue(validPost.validateTagsLength(), "The tags should be valid.");
    }

    @Test
    public void testValidateDifficultyLevel() {
        assertTrue(validPost.validateDifficultyLevel(), "The difficulty level should be valid.");
    }

    @Test
    public void testValidateEmergency() {
        assertTrue(validPost.validateEmergency(), "The emergency status should be valid.");
    }

    @Test
    public void testAddComment() {
        assertTrue(validPost.addComment("Valid comment"), "The comment should be added successfully.");
    }

    @Test
    public void testInvalidTitleLength() {
        String[] tags = {"tag1", "tag2"};
        Post invalidPost = new Post(2, "Short", "Valid body with more than 250 characters. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada.", tags, "Easy", "Ordinary");
        assertFalse(invalidPost.addPost(), "The post with invalid title should not be added.");
    }

    @Test
    public void testInvalidBodyLength() {
        String[] tags = {"tag1", "tag2"};
        Post invalidPost = new Post(3, "Valid Title", "Short body", tags, "Easy", "Ordinary");
        assertFalse(invalidPost.addPost(), "The post with invalid body should not be added.");
    }

    @Test
    public void testInvalidTagsLength() {
        String[] tags = {"tag1", "tag2", "tag3", "tag4", "tag5", "tag6"};
        Post invalidPost = new Post(4, "Valid Title", "Valid body with more than 250 characters. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada.", tags, "Easy", "Ordinary");
        assertFalse(invalidPost.addPost(), "The post with invalid tags should not be added.");
    }

    @Test
    public void testInvalidDifficultyLevel() {
        String[] tags = {"tag1", "tag2", "tag3", "tag4"};
        Post invalidPost = new Post(5, "Valid Title", "Valid body with more than 250 characters. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada.", tags, "Easy", "Ordinary");
        assertFalse(invalidPost.addPost(), "The post with invalid difficulty level should not be added.");
    }

    @Test
    public void testInvalidEmergency() {
        String[] tags = {"tag1", "tag2"};
        Post invalidPost = new Post(6, "Valid Title", "Valid body with more than 250 characters. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada.", tags, "Easy", "Immediately Needed");
        assertFalse(invalidPost.addPost(), "The post with invalid emergency should not be added.");
    }
}
