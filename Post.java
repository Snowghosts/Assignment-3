import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Post {
    //Different Variable types used for the validation
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList<String> postComments = new ArrayList<>();

    // Define post types and emergencies
    private final String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private final String[] postEmergencies = {"Immediately Needed", "Highly Needed", "Ordinary"};

    // These are the constructors for the assignment
    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    public boolean addPost() {
        if (!validateTitleLength() || !validateBodyLength() || !validateTagsLength() || !validateDifficultyLevel() || !validateEmergency()) {
            return false;
        }

        // All conditions met, add the post to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("post.txt", true))) {
            writer.write("Post ID: " + postID + "\n");
            writer.write("Title: " + postTitle + "\n");
            writer.write("Body: " + postBody + "\n");
            writer.write("Tags: " + String.join(", ", postTags) + "\n");
            writer.write("Difficulty: " + postType + "\n");
            writer.write("Emergency: " + postEmergency + "\n");
            writer.write("Comments: " + postComments + "\n");
            writer.write("----------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

     // Condition 1: Validate title length and starting characters
     private boolean validateTitleLength() {
        //Validate if the title length is within the parimeters
        return postTitle.length()>10 && postTitle.length() <= 250 && postTitle.substring(0,5).chars().allMatch(Character::isLetter);
    }

    // Condition 2: The post body should have a minimum of 250 characters.
    private boolean validateBodyLength() {
        //Validates if the body length is more or equals to 250 characters
        return postBody.length() >= 250;
    }

    // Condition 3: This part is used to validate the length
    private boolean validateTagsLength() {
        //This validates if the tag is not more than or less than the required amount
        if (postTags.length < 2 || postTags.length > 5) 
        return false;
        //This validates if the tag itself fits the requirements of having more than 2 words, less than 10 words and does not start with an uppercase
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) 
            {
                return false;
            }
        }
        return true;
    }

    // Condition 4: Classifying the difficulty
    private boolean validateDifficultyLevel() {
        //"Easy" posts should not have more than 3 tags
        if (postType.equals("Easy") && postTags.length > 3) {
            return false;
        }
         //"Very Difficult", "Difficult" posts should have a minimum of 300 characters in their body.
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) {
            return false;
        }
        return true;
    }

    // Condition 5: Validate emergency status based on difficulty
    private boolean validateEmergency() {
        //This is to validate that if the Post Type is easy the emergency type can only be ordinary
        if (postType.equals("Easy") && (postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed"))) {
            return false;
        }
        //This is to validate that if the Post Type is Very Difficult or Difficult the emergency type can only be Immediately Needed or Highly Needed
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postEmergency.equals("Ordinary")) {
            return false;
        }
        return true;
    }


    public boolean addComment(String comment) {
        // All conditions met, add the comment to the file
        postComments.add(comment);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("comment.txt", true))) {
            writer.write("Post ID: " + postID + "\n");
            writer.write("Comment: " + comment + "\n");
            writer.write("----------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

     // Condition 1: Validate comment length and first character
     private boolean inValidCommentLength(String comment) {
        String[] words = comment.split(" ");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }
        return true;
    }

        // Condition 2: Validate comment count based on post type and emergency
        private boolean ValidCommentLength() {
            if ((postTypes.equals("Easy") || postEmergencies.equals("Ordinary")) && postComments.size() >= 3) {
                return false;
            }
            if (postComments.size() >= 5) {
                return false;
            }
            return true;
        }
    // Example usage
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int postID = 0;
        boolean validID = false;
        String postTitle;
        String postBody;
        String[] postTags;
        String postType;
        String postEmergency;

        // Gather post details from the user
        while(!validID){
            try {
        System.out.print("Enter the Post ID: ");
        postID = Integer.parseInt(scanner.nextLine());
        validID = true;
            } catch (NumberFormatException e) { //if the post ID is a letter it will cause it to reset
                System.out.println("Your Input is invalid. Please enter a integer for post ID");
            }
        }

        //This is the input for the Post Title
        while(true){
            System.out.print("Enter Post Title: ");
            postTitle = scanner.nextLine();
            //Validates if the post title will be more than 10 characters and less or equals to 250 characters, and also no digits for the first 5 letters or unique characters 
            if(postTitle.length()>10 && postTitle.length() <= 250 && postTitle.substring(0,5).chars().allMatch(Character::isLetter)) 
            {
                break;
            }
            else
            {
                //send an invalid message out
                System.out.println("The title is Invalid. It is suppose to be 10-250 characters long and the first 5 characters must be letters");
            }
        }

        //This is the input for the body post and making sure that the post body only have more than or equals to 250 characters
        while(true){
            System.out.print("Enter Post Body: ");
            postBody = scanner.nextLine();
            if(postBody.length() >= 250)
            {
                break;
            }
            else
            {
                System.out.println("This body is invalid, it should be at least 250 characters");
            }
        }

        //This is the input for tags
        while (true) {
            System.out.print("Enter Post Tags (comma-separated, e.g., tag1,tag2): ");
            postTags = scanner.nextLine().split(",");
            boolean validTags = true;
            //This is to make sure that there is only 2 to 5 tags
            if (postTags.length < 2 || postTags.length > 5) {
                System.out.println("You must have 2 to 5 tags.");
                validTags = false;
            } else {
                //To make sure if the tag length only have 2 to 10 characters and it starts with a lower case
                for (String tag : postTags) {
                    if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                        System.out.println("Each tag must have at least 2 to 10 characters and must not include any upper-case letters.");
                        validTags = false;
                        break;
                    }
                }
            }

            if (validTags) {
                break;  // Exit the loop if all conditions are met
            }
        }

        //This is for the input of the difficulty level
        while (true) {
            // Get the post type from the user
            System.out.print("Enter Post Type (Very Difficult, Difficult, Easy): ");
            postType = scanner.nextLine();
            
            boolean validTags = true;
            boolean validBody = true;

            // Validate post body length based on post type
            if (postType.equalsIgnoreCase("Easy")) {
                if (postTags.length > 3) {
                    System.out.println("Easy posts should not have more than 3 tags.");
                    validTags = false;
                }
            } else if (postType.equalsIgnoreCase("Very Difficult") || postType.equalsIgnoreCase("Difficult")) {
                if (postBody.length() < 300) {
                    System.out.println("Very Difficult and Difficult posts should have a minimum of 300 characters in their body.");
                    validBody = false;
                }
            } else {
                System.out.println("Invalid post type. Please enter 'Very Difficult', 'Difficult', or 'Easy'.");
                continue; // Skip the rest and start the loop again
            }

            // Break the loop if all conditions are met
            if (validTags && validBody) {
                break;
            }
        }

        //This is for the input of the Emergency level
        while (true) {
            // Get the emergency status from the user
            System.out.print("Enter Post Emergency (Immediately Needed, Highly Needed, Ordinary): ");
            postEmergency = scanner.nextLine();

            //These whole code is use to validate the emergency level to make sure that the criteria suits the level
            if (postType.equalsIgnoreCase("Easy")) {
                if (postTags.length > 3) {
                    System.out.println("Easy posts should not have more than 3 tags.");
                }
                if (postEmergency.equalsIgnoreCase("Immediately Needed") || postEmergency.equalsIgnoreCase("Highly Needed")) {
                    System.out.println("Easy posts should not have the 'Immediately Needed' or 'Highly Needed' status.");

                }
            } else if (postType.equalsIgnoreCase("Very Difficult") || postType.equalsIgnoreCase("Difficult")) {
                if (postBody.length() < 300) {
                    System.out.println("Very Difficult and Difficult posts should have a minimum of 300 characters in their body.");

                }
                if (postEmergency.equalsIgnoreCase("Ordinary")) {
                    System.out.println("Very Difficult and Difficult posts should not have the 'Ordinary' status.");

                }
            } else {
                System.out.println("Invalid post type. Please enter 'Very Difficult', 'Difficult', or 'Easy'.");
                continue;
            }

            break;
        }
        

        // Create a Post object
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);

        // Attempt to add the post
        if (post.addPost()) {
            System.out.println("Post added successfully!");

            // Allow the user to add comments
            while (true) {
                System.out.print("Would you like to add a comment? (yes/no): ");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("yes")) {
                    System.out.print("Enter your comment: ");
                    String comment = scanner.nextLine();

                    if (post.addComment(comment)) {
                        System.out.println("Comment added successfully!");
                    } else {
                        System.out.println("Failed to add comment. It may not meet the required conditions.");
                    }
                } else {
                    break;
                }
            }
        } else {
            System.out.println("Failed to add post. It may not meet the required conditions.");
        }

        scanner.close();
      }      
    }

