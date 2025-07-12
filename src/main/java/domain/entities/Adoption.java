package domain.entities;

import java.time.LocalDate;

public abstract class Adoption {
    protected Adopter adopter;
    protected Employee employee;
    protected Pet pet;
    protected LocalDate dateAdoption;

    public Adoption(Adopter adopter, Employee employee, Pet pet) {
        this.adopter = adopter;
        this.employee = employee;
        this.pet = pet;
        this.dateAdoption = LocalDate.now();
    }

    public final void processAdoption() {
        registerAdopterData();
        registerEmployee();
        savePetData();
        specifiedSteps();
        Ticket ticket = generateTicket();
        ticket.print();
    }

    public Pet getPet() {
        return this.pet;
    }

    public Adopter getAdopter() {
        return this.adopter;
    }

    public LocalDate getDateAdoption() {
        return this.dateAdoption;
    }

    private void registerAdopterData() {
        System.out.println("Adopter: " + adopter.getName());
    }

    private void registerEmployee() {
        System.out.println("Employee: " + employee.getName());
    }

    private void savePetData() {
        System.out.println("Pet registered: " + pet.getName());
    }

    public Ticket generateTicket() {
        // Obtener las recomendaciones de cuidado
        var careInstructions = pet.getState().getCareInstructions();
        String recommendations = String.join("\n• ", careInstructions);
        if (!recommendations.isEmpty()) {
            recommendations = "• " + recommendations;
        }

        String ticketText = """
                🐾 TICKET DE ADOPCIÓN 🐾

                Empleado: %s
                Adoptante: %s (%d años)
                Dirección: %s

                Mascota: %s (%s)
                Nacimiento: %s | Peso: %.2f kg

                Fecha de adopción: %s

                📋 RECOMENDACIONES DE CUIDADO:
                %s

                ¡Gracias por adoptar! 🐕🐱🐰
                """.formatted(
                Employee.getInstance().getName(),
                adopter.getName(),
                adopter.getAge(),
                adopter.getAddress(),
                pet.getName(),
                pet.getSpecie(),
                pet.getBirthDate(),
                pet.getWeight(),
                this.getDateAdoption().toString(),
                recommendations);

        return new Ticket(this, ticketText);
    }

    protected abstract void specifiedSteps();
}
