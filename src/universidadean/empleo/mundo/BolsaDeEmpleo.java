/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnolog�a de la Informaci�n y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o m�s con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList<Aspirante> aspirantes;
    private int posicion;
    private List<Integer> aspirantesNombre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo() {
        aspirantes = new ArrayList<>();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public ArrayList<Aspirante> darAspirantes() {
        ArrayList<Aspirante> copia = new ArrayList<>(aspirantes);
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     *
     * @param nombreA           El nombre del aspirante - nombreA != null
     * @param profesionA        La profesi�n del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA A�os de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El tel�fono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retorn� true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        int aspiranteBuscado = buscarAspirante(nombreA, true);
        boolean agregado = false;
        if (aspiranteBuscado == -1) {
            Aspirante nuevoAspirante = new Aspirante(nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.add(nuevoAspirante);
            agregado = true;
        }

        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        // TODO: Realizar el ejercicio correspondiente
    	Collections.sort(this.aspirantes, new Comparator<Aspirante>() {
    	    @Override
    	    public int compare(Aspirante o1, Aspirante o2) {
    	        return o1.darNombre().compareTo(o2.darNombre());
    	    }
    	});
    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selecci�n <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por edad
     */
    public void ordenarPorEdad() {
        // TODO: Realizar el ejercicio correspondiente
    	Collections.sort(this.aspirantes, new Comparator<Aspirante>() {
    	    @Override
    	    public int compare(Aspirante o1, Aspirante o2) {
    	        return o1.darEdad() - o2.darEdad();
    	    }
    	});
    }

    /**
     * Organiza la lista de aspirantes por profesi�n usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesi�n
     */
    public void ordenarPorProfesion() {
        // TODO: Realizar el ejercicio correspondiente
    	Collections.sort(this.aspirantes, new Comparator<Aspirante>() {
    	    @Override
    	    public int compare(Aspirante o1, Aspirante o2) {
    	        return o1.darProfesion().compareTo(o2.darProfesion());
    	    }
    	});
    }

    /**
     * Organiza la lista de aspirantes por a�os de experiencia usando el algoritmo de inserci�n <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por a�os de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        // TODO: Realizar el ejercicio correspondiente
    	Collections.sort(this.aspirantes, new Comparator<Aspirante>() {
    	    @Override
    	    public int compare(Aspirante o1, Aspirante o2) {
    	        return o1.darAniosExperiencia() - o2.darAniosExperiencia();
    	    }
    	});
    }

    /**
     * Busca un Aspirante seg�n su nombre y retorna la posici�n en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retorn� la posici�n donde se encuentra un aspirante con el nombre dado. Si no se encuentra ning�n aspirante con ese nombre se retorn� -1.
     */
    public int buscarAspirante(String nombre, Boolean agregando) {
        this.posicion = -1;
        // TODO: B�squeda lineal por nombre
        if(!agregando) {
        	Aspirante encontrado = aspirantes.stream()
        	        .filter(aspirante -> {        	        	
        	        	this.posicion++;
        	        	return aspirante.darNombre().toLowerCase().contains(nombre.toLowerCase());
        	        	})
        	        .findAny()
        	        .orElse(null);
        }        
        
        return this.posicion;
    }

    /**
     * Busca un aspirante utilizando una b�squeda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retorn� la posici�n del aspirante con el nombre dado. Si el aspirante no existe se retorn� -1.
     */
    public int buscarBinarioPorNombre(String nombre) {
        this.posicion = -1;
        int ini = 0;
        int fin = aspirantes.size() - 1;
        while (fin >= ini) {
           int half = (int) Math.floor((ini + fin) / 2);
           if (aspirantes.get(half).darNombre().compareTo(nombre) == 0) {
               posicion = half;
               fin = -1;
           } else if (aspirantes.get(half).darNombre().compareTo(nombre) < 0)
               ini = half + 1;
           else
               fin = half - 1;
        }
        return posicion;
    }


    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante m�s joven. Si no hay aspirantes en la bolsa se retorn� -1
     */
    public int buscarAspiranteMasJoven() {
        int posicion = -1;

        // TODO: Realizar el ejercicio correspondiente
        this.aspirantesNombre = new ArrayList<>();
        this.aspirantes.forEach(aspirante->{
        	this.aspirantesNombre.add(aspirante.darEdad());
        });
        
        return this.aspirantesNombre.indexOf(Collections.min(this.aspirantesNombre));
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante con m�s edad. Si no hay aspirantes en la bolsa se retorn� -1
     */
    public int buscarAspiranteMayorEdad() {
        int posicion = -1;

        // TODO: Realizar el ejercicio correspondiente
        Collections.sort(this.aspirantes, new Comparator<Aspirante>() {
    	    @Override
    	    public int compare(Aspirante o1, Aspirante o2) {
    	        return o1.darEdad() + o2.darEdad();
    	    }
    	});
        
        this.aspirantesNombre = new ArrayList<>();
        this.aspirantes.forEach(aspirante->{
        	this.aspirantesNombre.add(aspirante.darEdad());
        });
        
        return this.aspirantesNombre.indexOf(Collections.max(this.aspirantesNombre));
        
    }

    /**
     * Busca el aspirante con m�s a�os de experiencia en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retorn� -1
     */
    public int buscarAspiranteMayorExperiencia() {
        int posicion = -1;

        // TODO: Realizar el ejercicio correspondiente

        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se elimin� el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retorn� true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) {
        boolean contratado = false;

        // TODO: Realizar el ejercicio correspondiente
        if(buscarAspirante(nombre, false) != -1 ) {
        	aspirantes.remove(buscarAspirante(nombre, false));
        	contratado = true;
        }
        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos a�os de experiencia <br>
     * son menores a la cantidad de a�os especificada <br>
     *
     * @param aniosExperiencia La cantidad de a�os con relaci�n a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) {
        int eliminados = 0;

        int cont = 0;
        int initialLenght = this.aspirantes.size();
        // TODO: Realizar el ejercicio correspondiente
        this.aspirantes = (ArrayList<Aspirante>) this.aspirantes.stream().filter(aspirante->aspirante.darAniosExperiencia() > aniosExperiencia).collect(Collectors.toList());
        return initialLenght - this.aspirantes.size();
    }

}