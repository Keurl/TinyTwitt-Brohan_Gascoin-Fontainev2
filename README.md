# TinyTwitt-Brohan_Gascoin-Fontainev2

projet de reproduction d'un Twitter basique via Google App Engine, Objectify et AngularJS

# <h1> Calcul de variance : temps de post d'un twitt sur 30 mesures:</h1>

# <h2> 100 followers </h2>

Voici les échantillons utilisés pour le calcul :

# <table border-collapse : collapse> 
<tr>
  <td> 0.6929 </td> 
  <td> 0.1914 </td>
  <td> 0.2065 </td>
  <td> 0.2025 </td>
  <td> 0.1822 </td>
  <td> 0.1868 </td>
  <td> 0.1655 </td>
  <td> 0.1663 </td>
  <td> 0.1747 </td>
  <td> 0.1625 </td>
  <td> 0.1852 </td>
  <td> 0.1952 </td>
  <td> 0.2441 </td>
  <td> 0.1660 </td>
  <td> 0.1739</td>
  <td> 0.1825 </td>
  <td> 0.1622 </td>
  <td> 0.1514 </td>
  <td> 0.1509 </td>
  <td> 0.1509 </td>
  <td> 0.1595 </td>
  <td> 0.1525 </td>
  <td> 0.1493 </td>
  <td> 0.1865 </td>
  <td> 0.1574 </td>
  <td> 0.1604 </td>
  <td> 0.1550 </td>
  <td> 0.1563 </td>
  <td> 0.1442 </td>
  <td> 0.1633 </td>
  <td> 0.1674 </td>
</tr>  
</table>

la moyenne est de : 0.18940333333333334

la variance est de : 0.009188452322222224

# <h2> 1000 followers </h2>

Voici les échantillons utilisés pour le calcul :

 <table border-collapse : collapse> 
  <tr> 
   <td> 0.5927</td><td>0.2508 </td><td> 0.2376</td><td> 0.2414</td><td>0.1925</td> <td>0.1806</td><td>0.1739</td><td>0.1733</td><td>0.1676</td><td>0.2422</td><td>0.1682</td><td>0.1741</td><td>0.1590</td><td>0.1606</td><td> 0.1617</td><td>0.1544</td><td>0.1541</td><td>0.1517</td><td>0.1555</td><td>0.1674</td> <td>0.1577</td><td>0.1455</td><td>0.1685</td><td>0.1460</td><td>0.1720</td><td>0.1466</td><td>0.1458</td><td>0.1474</td><td>0.1660</td><td>0.1490</td>
  </tr>  
 </table>
 
 la moyenne est de : 0.1867933333333334

 la variance est de : 0.006568519955555555

# <h2> 5000 followers ?</h2>

Pour le cas des 5000 followers et vu notre implémentation du projet, google ne nous fournis pas assez d'espace gratuit pour pouvoir effectuer nos tests. Une des solutions serait de trouver une autre implémentation du code, notamment ne pas dupliquer chaque tweet (cela donne de bonnes performances mais prend énormement de places, c'est la solution que nous avons choisis mais elle n'est pas idéale) et seulement récupérer le dernier tweet de chaque personne follow pour l'afficher dans une timeline.


#<h1> calcul de la variance : temps d'affichage de la timeline </h1>

<h2> Explications </h2>

Nous nous sommes rendu compte qu'au vu de notre implémentation du sujet, le nombre de personnes follow n'influence pas sur nos performance, puisque chacun se retrouve avec une copie du tweet de la personne suivie. De ce fait, notre implémentation permet un très bon temps d'exécution mais une très mauvaise gestion de la quantité de données.
