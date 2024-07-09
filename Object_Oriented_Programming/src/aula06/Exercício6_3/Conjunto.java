package aula06.Exercício6_3;



import java.util.ArrayList;

public class Conjunto {
    private ArrayList<Integer> intArr;

    public Conjunto() {
        this.intArr = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getintArr() {
        return intArr;
    }

    public void setintArr(ArrayList<Integer> intArr) {
        this.intArr = intArr;
    }

    public void insert(int n){
        if(contains(n)){
        }else{
        this.intArr.add(n);}
    }

    public boolean contains(int n){
        if(this.intArr.contains(n)){
            return true;
        }
        return false;
    }

    public void remove(int n){
        //if(this.intArr.contains(n)){
        //    this.intArr.remove(n);
        //}
        for(int i=0; i < size(); i++){
            if(this.intArr.get(i)==n){
                this.intArr.remove(i);
            }
        }
    }
    public void empty(){
        this.intArr.clear();
    }
    public int size(){
        return this.intArr.size();
    }

    public Conjunto combine(Conjunto add){
        Conjunto novaAdd = new Conjunto();
        for(int i = 0; i<add.size(); i++){
            novaAdd.insert(add.getintArr().get(i));
        }
        for(int i = 0; i<this.size(); i++){
            novaAdd.insert(this.intArr.get(i));
        }
        return novaAdd;
    }
    public Conjunto subtract(Conjunto dif){
        Conjunto novaDif = new Conjunto();
        for(int i = 0; i<this.size(); i++){
            novaDif.insert(this.intArr.get(i));
        }
        for(int i = 0; i<dif.size(); i++){
            novaDif.remove(dif.getintArr().get(i));
        }
        return novaDif;
    }
    public Conjunto intersect(Conjunto inter){
        Conjunto novaInter = new Conjunto();
        for(int i = 0; i<inter.size(); i++){
            for (int j = 0; j < this.intArr.size(); j++) {
                if(inter.getintArr().get(i)==this.intArr.get(j)){
                    novaInter.insert(inter.getintArr().get(i));
                }
            }
        }

        return novaInter;
    }
    @Override
    public String toString() {
        return intArr.toString();
    }
}

