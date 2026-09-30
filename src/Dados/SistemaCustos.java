package Dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SistemaCustos   {


    private ArrayList<Custo> custos;

    public SistemaCustos(ArrayList<Custo> custos) {
        this.custos = custos;
    }


    public void adicionarFuncionario(Custo c) {
        custos.add(c);
    }

    public void  printCustos ( ArrayList<Custo>  custosImpressos ) {

        sortarPorData(custosImpressos);

        for (Custo i : custosImpressos) {

           System.out.println(i.toString());


        }


    }

    public void excluirCustoMaisRecente()
    {
        sortarPorData(custos);

        var custoRecente = custos.getLast();

        custos.remove(custoRecente);
    }



    public ArrayList<Custo>  acharCustoPorDescricao (String descricao) {
        ArrayList<Custo> custosAchados = new ArrayList<>();;

        for (Custo i : custos) {

            if (i.getDescricao().contains(descricao)){
                custosAchados.add(i);

            }


        }

        printCustos(custosAchados);
        return custosAchados;
    }

    public ArrayList<Custo>  acharCustoPorData (Date data) {
        ArrayList<Custo> custosAchados = new ArrayList<>();;

        for (Custo i : custos) {

            if (i.getData().equals(data)){
                custosAchados.add(i);

            }


        }
        printCustos(custosAchados);
        return custosAchados;

    }

    public ArrayList<Custo>  acharCustoPorCategoria (CustoCategoria cat) {
        ArrayList<Custo> custosAchados = new ArrayList<>();;

        for (Custo i : custos) {

            if (i.getCusotCategoria() == cat ){
                custosAchados.add(i);

            }


        }
        printCustos(custosAchados);
        return custosAchados;
    }

    public ArrayList<Custo>  acharCustoPorDepartamento (Departamento dpt) {
        ArrayList<Custo> custosAchados = new ArrayList<>();;

        for (Custo i : custos) {

            if (i.getDepartamento() == dpt ){
                custosAchados.add(i);

            }


        }
        printCustos(custosAchados);
        return custosAchados;
    }

    public void sortarPorData(ArrayList<Custo>  custosDatados){

        Collections.sort(custosDatados);
    }

}
