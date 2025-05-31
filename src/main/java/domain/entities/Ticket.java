package domain.entities;

public class Ticket {
    private final Adoption adoption;

    public Ticket(Adoption adoption) {
        this.adoption = adoption;
    }

    public void print() {
        System.out.println("----------------------");
        System.out.println("ADOPTION TICKET");
        System.out.println("----------------------");
        System.out.println("Date and time of adoption: " + adoption.dateAdoption);

        System.out.println("Adopter information:");
        System.out.println("Name:" + " " + adoption.adopter.getName());
        System.out.println("Age:" + " " + adoption.adopter.getAge());
        System.out.println("Address:" + " " + adoption.adopter.getAddress());

        System.out.println("Pet information:");
        System.out.println("Name:" + " " + adoption.pet.getName());
        System.out.println("Birthdate:" + " " + adoption.pet.getBirthDate());
        System.out.println("Weight:" + " " + adoption.pet.getWeight());
        System.out.println("Specie:" + " " + adoption.pet.getSpecie());
        System.out.println("Recommendations:" + " " + adoption.pet.getState().getCareInstructions());

        System.out.println("Employee information:");
        System.out.println("Name:" + " " + adoption.employee.getName());
        System.out.println("Charge:" + " " + adoption.employee.getCharge());

        System.out.println("----------------------");
        System.out.println("¡Thank you for adopting!");
        System.out.println("----------------------");
    }
}
