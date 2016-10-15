/**
 * Created by chawki on 07/10/16.
 */
public class Compte {

    private double solde = 0;
    private int idcompte = 0;

    /*  TODO: Methodes */


    //id du compte
    public int getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(int idcompte) {
        this.idcompte = idcompte;
    }

    // pour faire un dépôt sur le compte.
    public void depot(double valeur) {
        solde += valeur;
    }

    // pour faire un retrait sur le compte.
    public void retrait(double valeur) {
        solde -= valeur;
    }


    // pour obtenir la valeur du solde ( Getters )
    public double getSolde() {
        return solde;
    }


    // pour faire un virement entre 2 comptes
    public void virer(double valeur, Compte destinataire) {
        retrait(valeur);
        destinataire.depot(valeur);

    }


    public int calculeInteret() {
        if (solde == 0) {
            return 0;
        } else if (solde > 0 && solde < 1000)
            return 2;
        else if (solde >= 1000 && solde < 10000)
            return 5;
        else return 15;
    }
}
//
//
