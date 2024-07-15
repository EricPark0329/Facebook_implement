package final1;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;
/**
 * 
 * A Profile class that handles each Profile (a person). This class contains :
 * name, profileImage, age, status, statusMesssage, isActive, and friends. Each
 * variable is independent and is user-friendly.
 * 
 * Profile class is meant and is used as a single-person, or a single account,
 * meaning that a single profile variable is equal to each person, or each
 * account.
 * 
 * ProfileManager class is meant to be used as handling bigger and external
 * terms, such as searching, adding, or removing friends or activating,
 * deactivating accounts.
 * 
 * Profile class is individual based class, whereas the ProfileManager class is
 * a server based class.
 * 
 */
public class Profile {
	private String name;
	private File profileImage;
	private int age;
	private String status;
	private String statusMessage;
	private Scanner sc;
	private boolean isActive;
	private String music;
	private String movie;
	private String hobby;
	CustomHashSet<Profile> friends;

	/**
	 * A constructor of the class profile. Using this constructor triggers Scanner
	 * for user input.
	 */
	public Profile() {
		sc = new Scanner(System.in);
		friends = new CustomHashSet<Profile>();
		isActive = true;
		this.setName();
		this.setAge();
		this.setProfileImage();
		this.setStatus();
		this.setStatusMessage();
	}

	/**
	 * A constructor of the class Profile. This constructor is only for
	 * test-purpose, where user can hard code each of variable. Each Null or invalid
	 * inputs will automatically be handled into empty String. The profileImage will
	 * be assigned as "defaultImage.jpg"
	 * 
	 * 
	 * @param name          the name of User (Profile)
	 * @param age           the age of User (Profile)
	 * @param status        the status of User (Profile). It should be either
	 *                      "Online, Offline, or Away." Default is "Online."
	 * @param statusMessage the statusMessage of User (Profile)
	 */
	public Profile(String name, int age, String status, String statusMessage, File profileImage) {
		if (name == null)
			this.name = "";
		else
			this.name = name;
		if (age < 0 || age > 120)
			this.age = 0;
		else
			this.age = age;
		if (status == null)
			this.status = "Online";
		else if (status.equalsIgnoreCase("online"))
			this.status = "Online";
		else if (status.equalsIgnoreCase("offline"))
			this.status = "Offline";
		else if (status.equalsIgnoreCase("away"))
			this.status = "Away";
		else
			this.status = "Online";
		if (statusMessage == null)
			this.statusMessage = "";
		else
			this.statusMessage = statusMessage;
		profileImage = new File("defaultImage.jpg");
		friends = new CustomHashSet<Profile>();
		isActive = true;
	}

	// --------------------------------------SETTER-----------------------------------------------

	/**
	 * Method used for setting the Status of a Profile. Calling this method will
	 * trigger Scanner for user interface.
	 * 
	 * It will let user choose their status as either Online, Offline, or Away.
	 * 
	 * By default, the method will assign the status as Online.
	 */
	public void setStatus() {
		boolean clear = false;
		status = "";
		while (!clear) {
			System.out.println("(STATUS)");
			System.out.println("1 : online / 2 : offline / 3 : Away");
			System.out.print("Please choose your status: ");
			if (sc.hasNextInt()) {
				int option = sc.nextInt();
				switch (option) {
				case 1:
					status = "Online";
					break;
				case 2:
					status = "Offline";
					break;
				case 3:
					status = "Away";
					break;
				default:
					System.out.println("Invalid input! Your status is by default : Online");
					status = "Online";
					break;
				}
			} else {
				System.out.println("Invalid input! Your status is by default : Online");
				status = "Online";
			}
			sc.nextLine();
			System.out.println("Your Status is: " + status);
			System.out.print("Is it correct? (y/n): ");
			String reply = sc.nextLine();
			if (reply.equalsIgnoreCase("y")) {
				clear = true;
			} else {
				System.out.println("");
			}
		}
		System.out.println("\nYour final status is: " + status + "\n");
	}

	/**
	 * Method used for setting the Name of a Profile. Calling this method will
	 * trigger Scanner for user interface.
	 * 
	 * It will let user choose their First, Middle, and Last Name. Middle name can
	 * be empty, or null, however the first name and the last name should be at
	 * least one character.
	 * 
	 */
	public void setName() {
		String firstName;
		String MiddleName;
		String lastName;
		boolean clear = false;
		name = "";
		while (!clear) {
			System.out.println("(NAME)");
			do {
				System.out.print("Please enter your First Name: ");
				firstName = sc.nextLine();
				if (firstName.isBlank()) {
					System.out.println("Invalid input. Please type in a character.\n");
				}
			} while (firstName.isBlank());
			name = firstName.trim() + " ";
			System.out.print("Please enter your Middle Name: ");
			MiddleName = sc.nextLine();
			if (!MiddleName.isBlank()) {
				name += MiddleName.trim() + " ";
			}
			do {
				System.out.print("Please enter your Last Name: ");
				lastName = sc.nextLine();
				if (lastName.isBlank()) {
					System.out.println("Invalid input. Please type in a character.\n");
				}
			} while (lastName.isBlank());
			name += lastName.trim();
			System.out.println("Your name is: " + name);
			System.out.print("Is it correct? (y/n): ");
			String reply = sc.nextLine();
			if (reply.equalsIgnoreCase("y")) {
				clear = true;
			} else {
				System.out.println("");
			}
		}
		System.out.println("\nYour final name is: " + name + "\n");
	}

	/**
	 * Method used for setting the Age of a Profile. Calling this method will
	 * trigger Scanner for user interface.
	 * 
	 * It will let user choose their age. Invalid character, or age below 0 or age
	 * above 120 will automatically set the age to 0.
	 * 
	 */
	public void setAge() {
		boolean clear = false;
		int age = 0;
		String reply = "";
		String temp = "";
		while (!clear) {
			System.out.println("(AGE)");
			System.out.print("Please enter your Age: ");
			if (sc.hasNextInt()) {
				age = sc.nextInt();
				if (120 > age && age > 0) {
					System.out.println("Your Age is: " + age);
					System.out.print("Is it correct? (y/n): ");
					reply = sc.next();
				} else {
					System.out.println("Invalid Age: " + age);
					System.out.println("Please retry");
				}
			} else {
				temp = sc.next();
				System.out.println("Your Age is: (Do not Wish to Enter)");
				System.out.print("Is it correct? (y/n): ");
				age = 0;
				reply = sc.next();
			}
			if (reply.equalsIgnoreCase("y")) {
				clear = true;
			} else {
				continue; // to look back to entering age
			}
		}
		System.out.println("\nYour final age is: " + age + "\n");
		this.age = age;
	}

	/**
	 * Method used for setting the statusMessage of a Profile. Calling this method
	 * will trigger Scanner for user interface.
	 * 
	 * User can freely set the status message of a profile.
	 * 
	 */
	public void setStatusMessage() {
		boolean clear = false;
		statusMessage = "";
		System.out.println("(STATUS MESSAGE)");
		System.out.print("Would you like to setup your status message? (y/n): ");
		String reply = sc.next();
		if (reply.equalsIgnoreCase("y")) {
			while (!clear) {
				sc.nextLine();
				System.out.print("Please enter your Status Message: ");
				statusMessage = sc.nextLine();
				clear = true;
			}
		}
		System.out.println("\nYour final status message is: " + statusMessage + "\n");
	}

	/**
	 * Method used for setting the profile image of a profile. Calling this method
	 * will trigger Scanner for user interface.
	 * 
	 * Calling this method will
	 * 
	 */
	public void setProfileImage() {
		sc.nextLine();
		System.out.println("(PROFILE IMAGE)");
		System.out.print("Would you like to setup your profile Image? (y/n): ");
		String reply = sc.next();
		if (reply.equalsIgnoreCase("y")) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");
			fileChooser.setFileFilter(imageFilter);
			File file = null;
			int result = fileChooser.showOpenDialog(null);

			while (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String extension = getFileExtension(selectedFile);
				if (extension != null && isSupportedImage(extension)) {
					System.out.println("Selected image file: " + selectedFile.getAbsolutePath());
					file = selectedFile;
					break;
				} else {
					System.out.println("Error: Unsupported image file format.");
					result = fileChooser.showOpenDialog(null);
				}
			}
		} else {
			System.out.println("Your profile image will be set to default");
			profileImage = new File("defaultImage.jpg");
		}
		System.out.println("\nSuccessfully added profile Image\n");
	}

	/**
	 * Method used for setting the user's favorite song in the profile.
	 * 
	 * @param music
	 * 
	 */
	public void setMusic(String music) {
		this.music = music;
	}

	/**
	 * Method used for setting the user's favorite song in the profile.
	 * 
	 * @param music
	 * 
	 */
	public void setMovies(String movie) {
		this.movie = movie;
	}

	/**
	 * Method used for setting the user's favorite song in the profile.
	 * 
	 * @param music
	 * 
	 */
	public void setHobbies(String hobby) {
		this.hobby = hobby;
	}

	/**
	 * Method used for setting the user's account activity to active or inactive.
	 **/
	public void setActive(boolean active) {
		this.isActive = !isActive;
	}

	/**
	 * Helper Method of setProfileImage() which obtains the file extension of a file
	 * 
	 * @param file an input file
	 * @return null if an extension does not exist, otherwise returns the extension,
	 *         such as "txt" or "jpg"
	 */
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? null : fileName.substring(dotIndex + 1).toLowerCase();
	}

	/**
	 * Helper Method of setProfileImage() which identifies if the extension is
	 * supported
	 * 
	 * @param extension a String input of extension, such as "txt" or "jpg"
	 * @return true if an extension is an image type, strictly "jpg", "jpeg" or
	 *         "png". False otherwise.
	 */
	private static boolean isSupportedImage(String extension) {
		return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
	}
	// --------------------------------------GETTER-----------------------------------------------

	/**
	 * Getter method for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for age
	 * 
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Getter method for profile image
	 * 
	 * @return profileImage
	 */
	public File getProfileImage() {
		return profileImage;
	}

	/**
	 * Getter method for status
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Getter method for statusMessage
	 * 
	 * @return statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * Getter method for active status
	 * 
	 * @return isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Getter method for friends
	 * 
	 * @return friends
	 */
	public CustomHashSet<Profile> getFriends() {
		return friends;
	}

	/**
	 * Prints out the user's profile
	 * 
	 * @param printMessage
	 **/
	public String printProfile() {
		String printMessage;

		if (music != null && movie != null && hobby != null) {
			printMessage = "\n-----------------------------------\n" + "USER PROFILE\n" + "Name: " + this.name + "\n"
					+ "Age: " + this.age + "\n" + "Profile Image: " + this.profileImage + "\n" + "Status: "
					+ this.status + "\n" + "Status Message: " + this.statusMessage + "\n" + "Favorite Music: "
					+ this.music + "\n" + "Favorite Movie: " + this.movie + "\n" + "Hobby: " + this.hobby + "\n"
					+ "-----------------------------------\n";
		} else {
			printMessage = "\n-----------------------------------\n" + "USER PROFILE\n" + "Name: " + this.name + "\n"
					+ "Age: " + this.age + "\n" + "Profile Image: " + this.profileImage + "\n" + "Status: "
					+ this.status + "\n" + "Status Message: " + this.statusMessage + "\n"
					+ "-----------------------------------\n";
		}
		return printMessage;
	}

	/**
	 * Comparing two Profile if they are equal.
	 * 
	 * @param other Profile to compare
	 * @return true if those two profile are equal, false otherwise
	 */
	public boolean equals(Profile other) {
		return (name.equals(other.getName())) && (age == other.getAge())
				&& (profileImage.equals(other.getProfileImage())) && (status.equals(other.getStatus()))
				&& (isActive == other.isActive()) && (statusMessage.equals(other.getStatusMessage()));
	}
	
	public String toString() {
		return name + ", " + age + ", " + profileImage + ", " + status + ", " + statusMessage;
	}

}
