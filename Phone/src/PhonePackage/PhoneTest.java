package PhonePackage;

public class PhoneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Iphone seansPhone = new Iphone("X", 100, "AT&T", "Gangstas Paradice");
		seansPhone.displayInfo();
		System.out.println(seansPhone.unlock());
		
		System.out.println(seansPhone.ring());
		
		System.out.println("MAKING A GALAXY PHONE NOW BELOW!!");
		
		Galaxy gbirdsPhone = new Galaxy("s9", 100, "verizon", "SongBird");
		gbirdsPhone.displayInfo();
		
		System.out.println(gbirdsPhone.unlock());
		
		System.out.println(gbirdsPhone.ring());
		
	}

}
