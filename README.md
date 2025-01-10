Voici un **README** et un **guide utilisateur** pour ton projet de bibliothèque virtuelle. Ces documents aideront ton examinateur à comprendre les fonctionnalités disponibles et à naviguer dans ton application.

---

## **README**

### **Description du projet**
Ce projet est une application de gestion de bibliothèque virtuelle écrite en Java. Elle permet aux utilisateurs de gérer une collection de livres, d'emprunter et de retourner des livres, de rechercher des livres par titre, auteur ou genre, et d'afficher des statistiques sur les livres disponibles et empruntés.

### **Fonctionnalités principales**
1. **Ajouter un livre** : Permet d'ajouter un nouveau livre à la bibliothèque.
2. **Rechercher un livre** : Permet de rechercher des livres par titre, auteur ou genre.
3. **Emprunter un livre** : Permet à un utilisateur d'emprunter un livre en spécifiant une partie du titre.
4. **Retourner un livre** : Permet à un utilisateur de retourner un livre emprunté.
5. **Afficher les statistiques** : Affiche la liste des livres disponibles et empruntés.
6. **Supprimer un livre** : Permet de supprimer un livre de la bibliothèque (s'il n'est pas emprunté).
7. **Quitter** : Quitte l'application.

### **Structure du projet**
- **`com.bibliotheque.models`** : Contient les classes `Livre` et `Utilisateur`.
- **`com.bibliotheque.services`** : Contient la classe `BibliothequeService` qui gère la logique métier.
- **`com.bibliotheque.main`** : Contient la classe `Main` qui gère l'interface utilisateur en ligne de commande.

### **Comment exécuter le projet**
1. Assure-toi d'avoir Java installé sur ton système.
2. Compile et exécute le fichier `Main.java` :
   ```bash
   javac com/bibliotheque/main/Main.java
   java com.bibliotheque.main.Main
   ```
3. Suis les instructions affichées dans le menu pour interagir avec l'application.

---

## **Guide utilisateur**

### **1. Ajouter un livre**
- **Option 1** dans le menu.
- Saisis les informations suivantes :
  - **Titre** : Le titre du livre.
  - **Auteur** : Le nom de l'auteur.
  - **Année** : L'année de publication.
  - **Genre** : Le genre du livre.
- Le livre sera ajouté à la bibliothèque.

### **2. Rechercher un livre**
- **Option 2** dans le menu.
- Choisis un critère de recherche :
  - **titre** : Recherche par titre.
  - **auteur** : Recherche par auteur.
  - **genre** : Recherche par genre.
- Saisis la valeur à rechercher.
- Les livres correspondants seront affichés.

### **3. Emprunter un livre**
- **Option 3** dans le menu.
- Saisis les informations suivantes :
  - **Nom utilisateur** : Ton nom.
  - **Prénom utilisateur** : Ton prénom.
  - **Email utilisateur** : Ton email.
  - **Titre ou partie du titre** : Une partie du titre du livre que tu souhaites emprunter.
- Si plusieurs livres correspondent, choisis celui que tu souhaites emprunter.
- Le livre sera marqué comme emprunté.

### **4. Retourner un livre**
- **Option 4** dans le menu.
- Saisis les informations suivantes :
  - **Nom utilisateur** : Ton nom.
  - **Prénom utilisateur** : Ton prénom.
  - **Email utilisateur** : Ton email.
  - **Titre du livre** : Le titre du livre à retourner.
- Le livre sera marqué comme disponible.

### **5. Afficher les statistiques**
- **Option 5** dans le menu.
- Affiche deux tableaux :
  - **Livres disponibles** : Liste des livres disponibles à l'emprunt.
  - **Livres empruntés** : Liste des livres actuellement empruntés, avec les informations de l'emprunteur.

### **6. Supprimer un livre**
- **Option 6** dans le menu.
- Saisis une partie du titre du livre que tu souhaites supprimer.
- Si plusieurs livres correspondent, choisis celui que tu souhaites supprimer.
- Le livre sera supprimé de la bibliothèque (s'il n'est pas emprunté).

### **7. Quitter**
- **Option 7** dans le menu.
- Quitte l'application.

---

## **Exemples d'utilisation**

### **Ajouter un livre**
```
=== Bibliothèque Virtuelle ===
1. Ajouter un livre
2. Rechercher un livre
3. Emprunter un livre
4. Retourner un livre
5. Afficher les statistiques
6. Supprimer un livre
7. Quitter
Choix : 1
Titre : Le Petit Prince
Auteur : Antoine de Saint-Exupéry
Année : 1943
Genre : Conte
Livre ajouté !
```

### **Rechercher un livre**
```
=== Bibliothèque Virtuelle ===
1. Ajouter un livre
2. Rechercher un livre
3. Emprunter un livre
4. Retourner un livre
5. Afficher les statistiques
6. Supprimer un livre
7. Quitter
Choix : 2
Rechercher par (titre/auteur/genre) : titre
Valeur : Prince
Le Petit Prince - Antoine de Saint-Exupéry (1943) [Conte]
```

### **Emprunter un livre**
```
=== Bibliothèque Virtuelle ===
1. Ajouter un livre
2. Rechercher un livre
3. Emprunter un livre
4. Retourner un livre
5. Afficher les statistiques
6. Supprimer un livre
7. Quitter
Choix : 3
Nom utilisateur : Alice
Prénom utilisateur : Dupont
Email utilisateur : alice@example.com
Titre ou partie du titre du livre : Potter
Livre 'Harry Potter à l'école des sorciers' emprunté avec succès par Alice.
```

### **Afficher les statistiques**
```
=== Bibliothèque Virtuelle ===
1. Ajouter un livre
2. Rechercher un livre
3. Emprunter un livre
4. Retourner un livre
5. Afficher les statistiques
6. Supprimer un livre
7. Quitter
Choix : 5

==========  Livres disponibles  ==========
+----------------------+----------------------+------+----------------------+------------+-------------------+
| Titre                | Auteur               | Année | Genre                | Emprunteur | Email             |
+----------------------+----------------------+------+----------------------+------------+-------------------+
| Le Petit Prince      | Antoine de Saint-Exupéry | 1943 | Conte                |            |                   |
+----------------------+----------------------+------+----------------------+------------+-------------------+

==========  Livres empruntés  ==========
+----------------------+----------------------+------+----------------------+------------+-------------------+
| Titre                | Auteur               | Année | Genre                | Emprunteur | Email             |
+----------------------+----------------------+------+----------------------+------------+-------------------+
| Harry Potter         | J.K. Rowling         | 1997 | Fantasy              | Alice      | alice@example.com |
+----------------------+----------------------+------+----------------------+------------+-------------------+
```

---

## **Conclusion**
Ce README et ce guide utilisateur fournissent une vue d'ensemble des fonctionnalités de ton application et expliquent comment les utiliser. Ils aideront ton examinateur à naviguer dans ton projet et à comprendre son fonctionnement. Si tu as besoin de modifications ou d'ajouts, n'hésite pas à demander ! 😊
