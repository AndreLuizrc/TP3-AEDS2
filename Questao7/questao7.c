#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct {
    char Lista[500];
}Lista;

typedef struct {
    char id[200];
    char name[200];
    Lista alternate_names;
    char house[200];
    char ancestry[200];
    char species[200];
    char patronus[200];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[200];
    bool alive;
    char dateOfBirth[200];
    int yearOfBirth;
    char eyeColour[200];
    char gender[200];
    char hairColor[200];
    bool wizard;
    
}Personagem;

typedef struct Celula Celula;

struct Celula{
    Personagem personagem;
    Celula *prox;
};

typedef struct {
    Celula *primeiro, *ultimo;
    int n;
} filaPersonagens;


Personagem construtor(char*, char*, char*, char*, char*, char*, char*, bool, bool, char*, bool, char*,
int, char*, char*, char*, bool);

Personagem construtor_vazio();

char* getId(Personagem*);

void setId(char*, Personagem*);

char* getName(Personagem*);

void setName(char*, Personagem*);

char* getAlternate_names(Personagem*);

void setAlternate_names(char*, Personagem*);

char* getHouse(Personagem*);

void setHouse(char*, Personagem*);

char* getAncestry(Personagem*);

void setAncestry(char*, Personagem*);

char* getSpecies(Personagem*);

void setSpecies(char*, Personagem*);

char* getPatronus(Personagem*);

void setPatronus(char*, Personagem*);

char* getHogwartsStaff(Personagem*);

void setHogwartsStaff(char*, Personagem*);

char* getHogwartsStudent(Personagem*);

void setHogwartsStudent(char*, Personagem*);

char* getActorName(Personagem*);

void setActorName(char*, Personagem*);

char* getAlive(Personagem*);

void setAlive(char*, Personagem*);

char* getDateOfBirth(Personagem*);

void setDateOfBirth(char*, Personagem*);

int getYearOfBith(Personagem*);

void setYearOfBith(int, Personagem*);

char* getEyeColour(Personagem*);

void setEyeColour(char*, Personagem*);

char* getGender(Personagem*);

void setGender(char*, Personagem*);

char* getHairColor(Personagem*);

void setHairColor(char*, Personagem*);

char* getWizard(Personagem*);

void setWizard(char*, Personagem*);

void imprimir(Personagem*);

char **ler(char*);

void PreencherVetor(Personagem*);

//-------------------Metodos Celula -------------------------------//
Celula *novaCelula(Personagem Personagem){
    Celula *nova = (Celula*) malloc(sizeof(Celula));
    nova->personagem = Personagem;
    nova->prox = NULL;

    return nova;
}

//--------------------Metodos Lista --------------------------------//
filaPersonagens *init(){
    filaPersonagens *fila = (filaPersonagens*) malloc(sizeof(filaPersonagens));
    fila->primeiro = novaCelula(construtor_vazio());
    fila->ultimo = fila->primeiro;
    fila->n = 0;
    return fila;
}

Personagem remover(filaPersonagens *fila){
    if(fila->n == 0){
        printf("Sem itens para remover\n");
        return construtor_vazio(); // Retornar um valor padrão ou nulo para Personagem
    }else{
        Celula *tmp = fila->primeiro;
        fila->primeiro = fila->primeiro->prox;
        Personagem result = fila->primeiro->personagem;
        fila->n--;
        free(tmp);

        return result;
    }
}

void mediaYearOfBirth(filaPersonagens *fila){
    int soma = 0;
    int j = 0;
    for(Celula *i = fila->primeiro->prox; i != NULL; i = i->prox){
        soma += i->personagem.yearOfBirth;
        j++;
    }

    printf(">> Year Birthday Average: %d\n", soma/j);
    //free(i);
}

void inserir(filaPersonagens *fila, Personagem personagem){
    if(fila->n >= 5){
        //printf("Sem espaço para inserção na fila\n");
        Personagem result = remover(fila);
    }
    
    fila->ultimo->prox = novaCelula(personagem);
    fila->ultimo = fila->ultimo->prox;
    fila->n++;

    mediaYearOfBirth(fila);
    
}

void imprimirFila(filaPersonagens *fila){

    printf("[Head]\n");
    Celula *i;
    int j;
    for(i = fila->primeiro->prox, j = 0; i != NULL; i = i->prox, j++){
        printf("[%d ## ", j);
        imprimir(&i->personagem);
    }
    printf("[Tail]");
    free(i);
}

//------------------------------------------------------------------//
void divComando(char *divComandos[], char comando[]){
    int i = 0;

    // Obtém o primeiro token
    divComandos[i] = strtok(comando, " ");

    // Caminha pelos outros tokens
    while (divComandos[i] != NULL && i < 3) {
        i++;
        divComandos[i] = strtok(NULL, " ");
    }
}

int main(){
    char id[200];
    int result;
    int numComands;
    char comando[200];
    char lixo[200];
    Personagem personagens[405];
    PreencherVetor(personagens);
    //printf("Iniciou");

    filaPersonagens *fila = init();

    scanf("%s", id);
    
    //leitura ids
    while(strcmp(id, "FIM") != 0){
        for(int i = 0; i < 405; i++){
            
            result = strcmp(personagens[i].id,id);
            
            if( result == 0){
                inserir(fila, personagens[i]);
                i = 500;
            }
        }
        scanf("%s", id);
    }

    scanf("%d", &numComands);

    //Para rodar no Verde "scanf("%99[^\n]%*c", lixo);"
    scanf("%99[^\n]%*c", lixo);
    //fgets(lixo,sizeof(lixo),stdin);
    
    //leitura comandos
    for(int i = 0; i < numComands; i++){

        //Para rodar no verde
        scanf("%99[^\n]%*c", comando);
        comando[strcspn(comando, "\r")] = '\0';
        //------------------------//

        //Para rodar no meu pc
        //fgets(comando, sizeof(comando), stdin);

        /*if(i != numComands-1){
            //comando[strlen(comando) - 1] = '\0';
        }*/
        //--------------------//

        //dividir comando//
        char *divComandos[3];
        divComando(divComandos, comando);
        //---------------//
        
        //Verificação do comando desejado//
        if(strcmp(divComandos[0], "I") == 0){
            for(int j = 0; j < 405; j++){
                if(strcmp(personagens[j].id,divComandos[1]) == 0){
                    inserir(fila, personagens[j]);
                    j = 500;
                }
            }
        }

        if(strcmp(divComandos[0], "R") == 0){
            Personagem tmp = remover(fila);
            printf("(R) %s\n", tmp.name);
        }
    }

    imprimirFila(fila);

    free(fila);
}

Personagem construtor(char id[], char name[], char alternate_names[], char house[], char ancestry[], char species[], char patronus[], bool hogwartsStaff, bool hogwartsStudent, char actorName[], bool alive, char dateOfBirth[],
int yearOfBirth, char eyeColour[], char gender[], char hairColor[], bool wizard){
    Personagem P;

    strcpy(P.id, id);
    strcpy(P.name, name);
    strcpy(P.alternate_names.Lista, alternate_names);
    strcpy(P.house, house);
    strcpy(P.ancestry, ancestry);
    strcpy(P.patronus, patronus);
    strcpy(P.species, species);
    P.hogwartsStaff = hogwartsStaff;
    P.hogwartsStudent = hogwartsStudent;
    strcpy(P.actorName, actorName);
    P.alive = alive;
    strcpy(P.dateOfBirth,dateOfBirth);
    P.yearOfBirth = yearOfBirth;
    strcpy(P.eyeColour, eyeColour);
    strcpy(P.gender, gender);
    strcpy(P.hairColor, hairColor);
    P.wizard = wizard;

    return P;
}

Personagem construtor_vazio(){
    Personagem P;

    strcpy(P.id, "");
    strcpy(P.name, "");
    strcpy(P.alternate_names.Lista, "");
    strcpy(P.house, "");
    strcpy(P.ancestry, "");
    strcpy(P.patronus, "");
    strcpy(P.species, "");
    P.hogwartsStaff = 0;
    P.hogwartsStudent = 0;
    strcpy(P.actorName, "");
    P.alive = 0;
    strcpy(P.dateOfBirth,"");
    P.yearOfBirth = 0;
    strcpy(P.eyeColour, "");
    strcpy(P.gender, "");
    strcpy(P.hairColor, "");
    P.wizard = 0;

    return P;
}

char **ler(char line[]){
    int tam_line = strlen(line);
    int start = 0;
    int count_campos = 0;
    char **campos = malloc(18 * sizeof(char*));

    for (int i = 0; i < 18; i++) {
        campos[i] = malloc(500);  // Aloca memória para cada string
    }


    for(int i = 0; i < tam_line; i++){
        if(line[i] == ';' && line[i-1] == ';'){
            strcpy(campos[count_campos], "");
            start = i + 1;
            count_campos++;
        } else if (line[i] == ';'){
            strncpy(campos[count_campos], line + start, i-start);
            campos[count_campos][i-start] = '\0';
            start = i + 1;
            count_campos++;
        }
    }

    strncpy(campos[17], line + start, tam_line-start-1);
    //campos[17][tam_line-start] = '\0';

    return campos;
}

void imprimir(Personagem *P){
    printf("%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
     P->id, P->name, P->alternate_names.Lista, P->house, P->ancestry, P->species, P->patronus,
     P->hogwartsStaff == 1? "true": "false", P->hogwartsStudent == 1? "true": "false", P->actorName, P->alive == 1? "true": "false", P->dateOfBirth, P->yearOfBirth, P->eyeColour, P->gender, P->hairColor, P->wizard == 1? "true": "false") ;
}

void PreencherVetor(Personagem personagens[]){
    FILE *arquivo_csv;
    char line[1200];
    if((arquivo_csv = fopen("/tmp/characters.csv", "r")) != NULL){
        
        int i = 0;
        int tam_lista;
        fgets(line,1200,arquivo_csv);
        while( fgets(line,1200,arquivo_csv) != NULL){
            char **atributos = ler(line);
            
            for(int i = 0; atributos[2][i] != '\0'; i++){
                if(atributos[2][i] == '['){
                    atributos[2][i] = '{';
                }else if(atributos[2][i] == '\''){
                    for (int j = i; atributos[2][j] != '\0'; j++) {
                        atributos[2][j] = atributos[2][j + 1];
                        
                    }
                }
            }

            tam_lista = strlen(atributos[2]);
            atributos[2][tam_lista-1] = '}';
            //printf("%s", atributos[17]);

            personagens[i] = construtor(atributos[0],atributos[1],atributos[2],atributos[3],atributos[4],
            atributos[5], atributos[6], strcmp(atributos[7], "VERDADEIRO") == 0? true: false, strcmp(atributos[8], "VERDADEIRO") == 0? true: false, atributos[9], atributos[10],
            atributos[12],atoi(atributos[13]), atributos[14], atributos[15], atributos[16], strcmp(atributos[17], "VERDADEIRO") == 0? true: false);
            
            i++;
            free(atributos);
        }
        fclose(arquivo_csv);
    }else {
        printf("Não foi possivel ler o arquivo");
    }

    
}

char* getId(Personagem *P){
    return P->id;
}

void setId(char *id, Personagem *P){
    strcpy(P->id, id);
}

char* getName(Personagem *P){
    return P->name;
}

void setName(char *name, Personagem *P){
    strcpy(P->name, name);
}

char* getAlternate_names(Personagem *P){
    return P->alternate_names.Lista;
}

void setAlternate_names(char *alternate_names, Personagem *P){
    strcpy(P->alternate_names.Lista, alternate_names);
}

char* getHouse(Personagem *P){
    return P->house;
}

void setHouse(char *house, Personagem *P){
    strcpy(P->house, house);
}

char* getAncestry(Personagem *P){
    return P->ancestry;
}

void setAncestry(char *species, Personagem *P){
    strcpy(P->species, species);
}

char* getSpecies(Personagem *P){
    return P->species;
}

void setSpecies(char *species, Personagem *P){
    strcpy(P->species, species);
}

char* getPatronus(Personagem *P){
    return P->patronus;
}

void setPatronus(char *patronus, Personagem *P){
    strcpy(P->patronus, patronus);
}

char* getHogwartsStaff(Personagem *P){
    int value = P->hogwartsStaff;
    return value == 1? "true": "false";
}

void setHogwartsStaff(char *hogwartsStaff, Personagem *P){
    if(strcmp(hogwartsStaff, "true") == 0){
        P->hogwartsStaff = 1;    
    }else {
        P->hogwartsStaff = 0;
    }
}

char* getHogwartsStudent(Personagem *P){
    int value = P->hogwartsStudent;
    return value == 1? "true": "false";
}

void setHogwartsStudent(char * hogwartsStudent, Personagem *P){
      if(strcmp(hogwartsStudent, "true") == 0){
        P->hogwartsStaff = 1;    
    }else {
        P->hogwartsStaff = 0;
    }
}

char* getActorName(Personagem *P){
    return P->actorName;
}

void setActorName(char *actorName, Personagem *P){
    strcpy(P->actorName, actorName);
}

char* getAlive(Personagem *P){
    int value = P->alive;
    return value == 1? "true": "false";
}

void setAlive(char *alive, Personagem *P){
    if(strcmp(alive, "true") == 0){
        P->alive = 1;    
    }else {
        P->alive = 0;
    }
}

char* getDateOfBirth(Personagem *P){
    return P->dateOfBirth;
}

void setDateOfBirth(char *dateOfBirth, Personagem *P){
    strcpy(P->dateOfBirth, dateOfBirth);
}

int getYearOfBith(Personagem *P){
    return P->yearOfBirth;
}

void setYearOfBith(int yearOfBirth, Personagem *P){
    P->yearOfBirth = yearOfBirth;
}

char* getEyeColour(Personagem *P){
    return P->eyeColour;
}

void setEyeColour(char *eyeColour, Personagem *P){
    strcpy(P->eyeColour, eyeColour);
}

char* getGender(Personagem *P){
    return P->gender;
}

void setGender(char *gender, Personagem *P){
    strcpy(P->gender, gender);
}

char* getHairColor(Personagem *P){
    return P->hairColor;
}

void setHairColor(char *hairColor, Personagem *P){
    strcpy(P->hairColor, hairColor);
}

char* getWizard(Personagem *P){
    int value = P->wizard;
    return value == 1? "true": "false";
}

void setWizard(char *wizard, Personagem *P){
    if(strcmp(wizard, "true") == 0){
        P->wizard = 1;    
    }else {
        P->wizard = 0;
    }
}