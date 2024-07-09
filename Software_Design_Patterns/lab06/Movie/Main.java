public class Main {
    public static void main(String[] args) {
        MovieBuilder builder = new MovieBuilder();
        builder.setDirector(new Person("Rafael Direito")).setSeries("Aulas de PDS").setTitle("Aula 6").setWriter(new Person("António Neves"));
        Movie m = builder.build();
        System.out.println(m);
    }
}
