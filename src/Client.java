import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chawki on 07/10/16.
 */

public class Client {

    private List<Compte> comptes = new ArrayList<>();
    private int nombreCompte = 0;
    private String nom = "none";
    private static int compteNum = 0;
    private Scanner clavier;


    public Client(String nom) {
        this.nom = nom;
        creationCompteClient();
    }


    public void creationCompteClient() {
        compteNum++;
        comptes.add(new Compte());
        comptes.get(nombreCompte).setIdcompte(compteNum);
        nombreCompte++;


    }

    public int getNombreCompte() {
        return nombreCompte;
    }

    public String getNom() {
        return nom;
    }
/*certaines fonctions n'appartiennes pas à Client comme Menu */
    //affiche toute les info des ou de compte(s) du client
    public void infoCompte() {
        for (int i = 0; i < comptes.size(); i++) {
            System.out.println("---> compte n°:" + (i + 1) + " [id:" + comptes.get(i).getIdcompte() + "]" + "Solde = " + comptes.get(i).getSolde() + " DA, avec un taux d'interet de " + comptes.get(i).calculeInteret() + "%");
        }

    }
    //n'affiche que le numero du compte et sont solde
    public void afficherCompte() {
        for (int i = 0; i < comptes.size(); i++) {
            System.out.println("compte " + (i + 1) + " : " + comptes.get(i).getSolde() + " DA");
        }
    }

    public void menuClient() {

        boolean fini = false;

        while (!fini) {

            System.out.println("\n==========================  Menu  ==================================");
            System.out.println("Quelle operation voulez-vous effectuer sur le client ," + nom);
            System.out.println(" 1) Faire un dépot");
            System.out.println(" 2) Faire un retrait");
            System.out.println(" 3) Faire un virement");
            System.out.println(" 4) Créer un compte ");
            System.out.println(" 5) Afficher le bilan des comptes");
            System.out.println(" 6) Revenir au menu principal");
            System.out.println("====================================================================\n");

            clavier = new Scanner(System.in);
            System.out.print("Votre choix: ");
            String reponse = clavier.nextLine();
            System.out.println();


            switch (reponse) {

                case "1":
                    System.out.print("De quel montant ? : ");
                    double montantPlus = clavier.nextDouble();
                    int choixCompte = 1;

                    choixCompte = getChoixCompte(choixCompte);

                    comptes.get(choixCompte - 1).depot(montantPlus);
                    System.out.println("Le depot a été effectué\n");
                    infoCompte();

                    break;


                case "2":
                    System.out.print("De quel montant ? : ");
                    double montantmoin = clavier.nextDouble();
                    choixCompte = 1;

                    choixCompte = getChoixCompte(choixCompte);
                    if (comptes.get(choixCompte - 1).getSolde() < montantmoin)
                        System.out.println("Retrait impossible , montant superieur au solde du compte !");
                    else {
                        comptes.get(choixCompte - 1).retrait(montantmoin);
                        System.out.println("Le retrait a été effectué\n");
                        infoCompte();
                    }
                    break;


                case "3":

                    if (nombreCompte > 1) {
                        Scanner clavier = new Scanner(System.in);
                        System.out.print("De quel montant ? : ");
                        double montantVir = clavier.nextDouble();

                        System.out.println("\nVos comptes :");
                        afficherCompte();

                        System.out.print("\nCompte emetteur : ");
                        int emetteur = clavier.nextInt();

                        System.out.print("Compte destinataire : ");
                        int destinataire = clavier.nextInt();

                        // permet de tester si le numero de compte en saisi ne depasse pas le nombre de compte total
                        boolean test1 = (emetteur <= nombreCompte) && (destinataire <= nombreCompte);
                        // permet de tester si le montant debiter du compte emetteur est inferieur au solde de se compte
                        boolean test2 = comptes.get(emetteur-1).getSolde() >= montantVir;

                        if (test1 && nombreCompte > 1 && test2) {
                            comptes.get(emetteur-1).virer(montantVir, comptes.get(destinataire-1));
                            System.out.println("\nLe virement a étté effectué\n");
                            infoCompte();

                        } else {
                            System.out.println("\nUn des comptes n'existe pas ou le montant est superieur au solde du compte ! \n");
                        }
                    } else System.out.println("Vous n'avez qu'un seul compte ! \n");


                    break;


                case "4":
                    creationCompteClient();
                    System.out.println("le compte n°" + nombreCompte + " a ete creer ");

                    break;


                case "5":
                    infoCompte();
                    break;


                case "6":
                    fini = true;
                    System.out.println("Retour en arriere ! \n");
                    break;

                default:
                    System.out.println("erreur de saisi , recomencer ! \n");
                    break;

            }
        }

    }

    //methode pour choisir un compte (pour eviter les doublant de code)
    private int getChoixCompte(int choixCompte) {
        if (nombreCompte > 1) {
            System.out.println("\nSur quelle compte ? : ");
            afficherCompte();
            System.out.print("\nvotre choix : ");
            choixCompte = clavier.nextInt();
        }
        return choixCompte;
    }


}

