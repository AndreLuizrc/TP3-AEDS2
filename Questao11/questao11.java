

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public Celula prox, ant;

    public Celula(){
        this.personagem = null;
        this.prox = null;
        this.ant = null;
    }

    public Celula(Personagem personagem){
        this.personagem = personagem;
        this.prox = null;
        this.ant = null;
    }
}

class ListPersonagens {
    public Celula primeiro, ultimo;

    ListPersonagens(){
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(Personagem personagem){

        Celula tmp = new Celula(personagem);

        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        tmp.ant = primeiro;
        tmp.prox.ant = tmp;

        if(primeiro == ultimo){
            ultimo = tmp;
        }

        tmp = null;
    }

    public void inserir(Personagem personagem, int pos) throws Exception{

        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho){
            throw new Exception("Posição invalida");
        }else if(pos == 0){
            inserirInicio(personagem);
        }else if(pos == tamanho){
            inserirFim(personagem);
        }else{
            Celula i = primeiro;

            for(int j = 0; j < pos; i = i.prox, j++);

            Celula tmp = new Celula(personagem);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp.ant = i;
            tmp.prox.ant = tmp;

            i = tmp = null;
        }

    }

    public void inserirFim(Personagem personagem){
        
        Celula tmp = new Celula(personagem);

        ultimo.prox = tmp;
        tmp.ant = ultimo;
        ultimo = tmp;
        tmp = null;
    }

    public Personagem removerInicio() throws Exception{

        if(primeiro == ultimo){
            throw new Exception("Sem itens para remover");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        tmp.prox = null;
        primeiro.ant = null;
        tmp = null;

        return primeiro.personagem;
    }

    public Personagem remover(int pos) throws Exception{

        int tamanho = tamanho();
        Personagem result;
        if(pos < 0 || pos >  tamanho || ultimo == primeiro){
            throw new Exception("Posição invaldia");
        }else if(pos == 0){
            result = removerInicio();
        }else if(pos == tamanho){
            result = removerFim();
        }else{

            Celula i = primeiro;

            for(int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = i.prox;
            result = tmp.personagem;
            i.prox = tmp.prox;
            i.prox.ant = i;
            tmp.prox = null;
            tmp.ant = null;
            tmp = null;

        }
        return result;
    }

    public Personagem removerFim() throws Exception{

        Personagem result;
        if (primeiro == ultimo) {
            throw new Exception("Sem itens para remover");
        }

        Celula i = ultimo;
        result = ultimo.personagem;
        ultimo = ultimo.ant;
        ultimo.prox = null;
        i.ant = null;

        i = null;

        return result;
    }

    public int tamanho(){
        int tam = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }

    public Personagem getPersonagem(int pos){
        Celula tmp = primeiro;
        for(int i = 0; i <= pos; i++, tmp = tmp.prox);
        return tmp.personagem;
    }

    public void imprimir(){
        
        for(Celula i = primeiro.prox; i != null; i = i.prox){
           i.personagem.imprimir();
        }
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

public class questao11 {

    public static void preencherVetor(Personagem[] personagens){
        String line;
        String[] atributos;
        Lista alternate_names;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfBirth;
        try {
            Scanner sc = new Scanner(new File("/tmp/characters.csv"));
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

    public static void swap(ListPersonagens lista, int i, int j){
        Personagem tmpPersonagem = lista.getPersonagem(i);
        
        Celula tmp = lista.primeiro.prox;
        for(int k = 0; k < i; k++, tmp = tmp.prox);
        tmp.personagem = lista.getPersonagem(j);

        tmp = lista.primeiro.prox;
        for(int k = 0; k < j; k++, tmp = tmp.prox);
        tmp.personagem = tmpPersonagem;
    }

    public static void QuickSort(ListPersonagens lista, int esq, int dir,int[] comp_mov) {
        int i = esq;
        int j = dir;

        Personagem pivo = lista.getPersonagem((esq+dir)/2);

        while( i <= j){
            while(lista.getPersonagem(i).getHouse().compareTo(pivo.getHouse()) <= 0){
                if(lista.getPersonagem(i).getHouse().compareTo(pivo.getHouse()) == 0){
                    if(lista.getPersonagem(i).getName().compareTo(pivo.getName()) < 0){
                        i++;
                        comp_mov[0] += 3;
                    }else{
                        comp_mov[0] +=3;
                        break;
                    }
                }else{
                    i++;
                    comp_mov[0] +=2 ;
                }
            }
    
            while(lista.getPersonagem(j).getHouse().compareTo(pivo.getHouse()) >= 0){
                if(lista.getPersonagem(j).getHouse().compareTo(pivo.getHouse()) == 0){
                    if(lista.getPersonagem(j).getName().compareTo(pivo.getName()) > 0){
                        j--;
                        comp_mov[0] +=3;
                    }else{
                        comp_mov[0] +=3;
                        break;
                    }
                }else{
                    j--;
                    comp_mov[0] +=2;
                }
            }
    
            if(i <= j){
                swap(lista, i, j);
                i++;
                j--;
                comp_mov[1] += 3;
            }
        }

        if( i < dir ){
            QuickSort(lista,i,dir,comp_mov);
        }

        if(j > esq){
            QuickSort(lista, esq, j,comp_mov);
        }
    }

    public static void log(long tempoExecucao,int comparacoes, int movimentacoes){
        File log = new File("824007_quickSort2_java.txt");
        double segundos =tempoExecucao / 1_000_000_000.0;

        try{
            PrintWriter writer = new PrintWriter( new FileWriter(log, true));
            writer.println("824007\t"+comparacoes+"\t"+movimentacoes+"\t"+segundos);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Personagem[] personagens = new Personagem[405];
        preencherVetor(personagens);
        int[] comp_mov = {0,0};

        //Inicialização lista de personagens
        ListPersonagens lista = new ListPersonagens();

        String id;

        id = sc.nextLine();
        while(id.equals("FIM") != true){
            for(int i = 0; i < 405; i++){
                if(personagens[i].getId().equals(id)){
                    try {
                        lista.inserirFim(personagens[i]); //inserção dos personagens correspondentes as entradas na lista
                        i = 410;
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
            id = sc.nextLine();
        }

        long inicio = System.nanoTime();

        QuickSort(lista, 0, lista.tamanho() - 1, comp_mov);

        long fim = System.nanoTime();
        
        long tempoExecucao = fim - inicio;
        
        log(tempoExecucao, comp_mov[0], comp_mov[1]);

        lista.imprimir();

        sc.close();
    }

}


