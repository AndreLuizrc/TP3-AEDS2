package questao6;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lista {
    private List<String> Lista;

    Lista(){
        this.Lista = new ArrayList<>();
    }

    public List<String> getLista() {
        return Lista;
    }

    public void setLista(List<String> lista) {
        Lista = lista;
    }

    public List<String> parseStringToList(String texto){
        // Expressão regular para encontrar strings entre aspas simples
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(texto);
        
        // Iterar sobre as correspondências encontradas e adicionar à lista
        while (matcher.find()) {
            Lista.add(matcher.group(1)); // Adiciona o texto capturado entre as aspas simples
        }

        return Lista;
        
    }
}

class Celula{
    public Personagem personagem;
    public Celula prox;

    public Celula(){
        this.personagem = null;
        this.prox = null;
    }

    public Celula(Personagem personagem){
        this.personagem = personagem;
        this.prox = null;
    }
}

class ListPersonagens {
    private Celula topo;

    ListPersonagens(){
        topo = null;
    }


    public void inserir(Personagem personagem){
        
        Celula tmp = new Celula(personagem);

        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }



    public Personagem remover() throws Exception{

        Personagem result;
        if (topo == null) {
            throw new Exception("Sem itens para remover");
        }

        result = topo.personagem;
        Celula tmp = topo;
        topo = tmp.prox;
        tmp.prox = null;
        tmp = null;

        return result;
    }

    public void imprimir(){
        int cont = 0;
        System.out.println("[Top]");
        for(Celula i = topo; i != null; i = i.prox, cont++){
           System.out.println("["+ cont +" "+ i.personagem.getAtributoString());
        }
        System.out.println("[Bottom]");
    }
}

class Personagem {
    private String id;
    private String name;
    private List<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwatsStudent;
    private String actorName;
    private boolean alive;
    private Date dateOfBirth;
    private int yearOfBith;
    private String eyeColour;
    private String gender;
    private String hairColor;
    private boolean wizard;



    public Personagem(String id, String name, List<String> alternate_names, String house, String ancestry, String species,
            String patronus, boolean hogwartsStaff, boolean hogwatsStudent, String actorName, boolean alive,
            Date dateOfBirth, int yearOfBith, String eyeColour, String gender, String hairColor, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwatsStudent = hogwatsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBith = yearOfBith;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColor = hairColor;
        this.wizard = wizard;
    }

    public Personagem() {
        this.id = "";
        this.name = "";
        this.alternate_names = new ArrayList<>();
        this.house = "";
        this.ancestry = "";
        this.species = "";
        this.patronus = "";
        this.hogwartsStaff = false;
        this.hogwatsStudent = false;
        this.actorName = "";
        this.alive = false;
        this.dateOfBirth = Date.from(null);
        this.yearOfBith = 0;
        this.eyeColour = "";
        this.gender = "";
        this.hairColor = "";
        this.wizard = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(List<String> alternate_names) {
        this.alternate_names = alternate_names;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean getHogwatsStudent() {
        return hogwatsStudent;
    }

    public void setHogwatsStudent(boolean hogwatsStudent) {
        this.hogwatsStudent = hogwatsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBith() {
        return yearOfBith;
    }

    public void setYearOfBith(int yearOfBith) {
        this.yearOfBith = yearOfBith;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public void imprimir(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("[" + id +" ## " + name +" ## "+ alternate_names.toString().replace('[', '{').replace(']', '}') + " ## " + house + " ## " + ancestry + " ## " + species + " ## " + patronus + " ## " + hogwartsStaff + 
        " ## " + hogwatsStudent + " ## " + actorName + " ## " + alive + " ## " + formatter.format(dateOfBirth) + " ## " + yearOfBith + " ## " + eyeColour + " ## " + gender + " ## " + hairColor + " ## " + wizard + "]");
    }

    public String getAtributoString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String data = "## " + id +" ## " + name +" ## "+ alternate_names.toString().replace('[', '{').replace(']', '}') + " ## " + house + " ## " + ancestry + " ## " + species + " ## " + patronus + " ## " + hogwartsStaff + 
        " ## " + hogwatsStudent + " ## " + actorName + " ## " + alive + " ## " + formatter.format(dateOfBirth) + " ## " + yearOfBith + " ## " + eyeColour + " ## " + gender + " ## " + hairColor + " ## " + wizard + "]";

        return data;
    }

    public Personagem clonar(){
        return this;
    }

    public static String[] ler(String dados){
        String[] atributos = dados.split(";");
    
        return atributos;
    }

}

public class questao6 {

    public static void preencherVetor(Personagem[] personagens){
        String line;
        String[] atributos;
        Lista alternate_names;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfBirth;
        try {
            Scanner sc = new Scanner(new File("./characters.csv"));
            int i = 0;
            sc.nextLine();
            while(sc.hasNextLine()){
                line = sc.nextLine();
                atributos = Personagem.ler(line);
                alternate_names = new Lista();
                try {
                    dateOfBirth = formatter.parse(atributos[12]);
                    personagens[i] = new Personagem(atributos[0],atributos[1],alternate_names.parseStringToList(atributos[2]), atributos[3], atributos[4], atributos[5], atributos[6], atributos[7].equals("VERDADEIRO")? true: false, atributos[8].equals("VERDADEIRO")? true: false, atributos[9], 
                    atributos[10].equals("VERDADEIRO")? true: false,dateOfBirth, Integer.parseInt(atributos[13]),atributos[14],atributos[15],atributos[16], atributos[17].equals("VERDADEIRO")? true: false);
                    //System.out.println(personagens[i].getId());
                    i++;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Personagem[] personagens = new Personagem[405];
        preencherVetor(personagens);

        //Inicialização lista de personagens
        ListPersonagens lista = new ListPersonagens();

        String id;
        int numComands;
        String comando;

        id = sc.nextLine();
        while(id.equals("FIM") != true){
            for(int i = 0; i < 405; i++){
                if(personagens[i].getId().equals(id)){
                    try {
                        lista.inserir(personagens[i]); //inserção dos personagens correspondentes as entradas na lista
                        i = 410;
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
            id = sc.nextLine();
        }

        numComands = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < numComands; i++){
            comando = sc.nextLine();
            String[] divComando = comando.split(" ");

            if(divComando[0].equals("I")){
                for(int j = 0; j < 405; j++){
                    if(personagens[j].getId().equals(divComando[1])){
                        try {
                            lista.inserir(personagens[j]); //inserção dos personagens correspondentes as entradas na lista
                            j = 410;
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                }
            }

            
            if(divComando[0].equals("R")){
                try {
                    Personagem tmp = lista.remover();
                    System.out.println("(R) "+ tmp.getName());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e);
                }
                
            }

        }


        lista.imprimir();

        sc.close();
    }

}


