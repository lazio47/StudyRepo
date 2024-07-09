public class Facade extends Company3 {

    public Facade() {
        super();
    }

    public void admitEmployee(Person3 p, double salary) {
        super.admitEmployee(p, salary);

        // Sem instanciar dava erro

        SocialSecurity.regist(p);
        Card card = new Card();
        card.createCard(p);
        Parking parking = new Parking(super.getCompany());
        parking.allow(p);
        Insurance insurance = new Insurance();
        insurance.regist(p);
    }
}
