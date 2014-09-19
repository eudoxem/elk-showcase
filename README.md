Logstash + ElasticSearch + Kibana Showcase
========

Un exemple d'utilisation de ses 3 solutions de logs complémentaires

1- Logstash
Logstash est un outil de gestion d'événements et de logs qui peut être utilisé pour collecter, parser et stocker les logs afin de les utiliser plus tard (recherche, métriques, etc).  Il est libre et totalement open-source.

a) Téléchargement
La dernière version de Logstash est téléchargeable depuis http://logstash.net/. La version utilisée pour ce showcase est la 1.4.2 et est disponible dans le projet sous github.

b) Pré-requis
Afin de pouvoir utiliser Logstash, il faut qu'il y ait Java installé sur la machine. Aucune version n'est précisée mais la documentation recommande d'utiliser une version récente pour tirer le meilleur parti de Logstash. Nous utiliserons pour nos tests Java  1.7

c) Lancement et configuration
Logstash utilise un agent pour collecter, parser et stocker les logs qui lui sont transmis. Ainsi, pour faire tourner Logstash il faut lancer l'agent en lui fournissant certains paramètres de configuration. La commande est de la forme %LOGSTASH_HOME%/bin/logstash agent -options et se lance dans un terminal.

Les options généralement utilisées sont:
- e : pour dire à Logstash que la configuration à utiliser pour son traitement lui sera fournie directement dans la ligne de commande  (ex: bin/logstash -e 'input { stdin { } } output { stdout { codec => rubydebug } }')
- f : pour dire à Logstash d'utiliser la configuration dans le fichier en paramètre pour son traitement (ex: bin/logstash -f logstash.conf)

Les autres options sont consultables à l'adresse suivante: http://logstash.net/docs/1.4.2/flags.

d) Processus de traitement Logstash
A la réception d'un évenement, Logstash se base sur une chaine de composants présents dans le fichier de configuration. Les composants utilisés sont les suivants:
- input : c'est la première phase du pipeline Logstash. Un input représente la source des logs (méchanisme de passage des logs à Logstash). Les inputs supportés de base par Logstash sont regroupés dans %LOGSTASH_HOME%/lib/logstash/inputs. La liste des inputs fréquemment utilisés peut être visualisée à http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (§Inputs)

- filter : c'est la phase intermédiaire du pipeline Logstash. Dans cette phase, on peut enchainer plusieurs filtres. Un filtre permet d'effectuer des traitements et peut être couplée avec des conditions pour effectuer certaines actions. Les filtres sont en fait des expressions régulières prédéfinies et Logstash vient avec un certain nombre de filtres qui sont regroupés dans %LOGSTASH_HOME%/lib/logstash/filters. La liste des filtres fréquemment utilisés peut être visualisée à http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (§Filters)

- output : c'est la dernière phase du pipeline Logstash. Un output représente la sortie vers laquelle sont redirigés les résultats des traitements Logstash. Les outputs supportés de base par Logstash sont regroupés dans %LOGSTASH_HOME%/lib/logstash/outputs. La liste des outputs fréquemment utilisés peut être visualisée à http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (§Outputs)

- codec : c'est un filtre de flux qui peut être utilisé dans un input ou un output. Les codecs permettent de facilement séparer le processus de transport des messages de celui de la sérialisation. La liste des codecs fréquemment utilisés peut être visualisée à http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (§Codecs)

En règle générale, un fichier de configuration Logstash contient toujours un input qui est la source des logs (peut être la console ou un/plusieurs fichier(s)), un ou plusieurs filtres (qui correspondent aux actions effectuées lors du parsing des logs) et un output qui est la sortie (peut être la console ou ElasticSearch par exemple).


2- ElasticSearch
ElasticSearch est un moteur open-source de recherche full-text qui utilise une base de données NoSQL. Il est basé sur Lucène et permet de stocker, rechercher et analyser rapidement de grands volumes de données quasiment en temps réel. It allows you to store, search, and analyze big volumes of data quickly and in near real time

a) Téléchargement
La dernière version d'ElasticSearch est téléchargeable depuis http://www.elasticsearch.org/overview/elkdownloads/. La version utilisée pour ce showcase est la 1.3.2 et est disponible dans le projet sous github.

b) Pré-requis
Afin de pouvoir utiliser ElasticSearch, il faut qu'il y ait Java 1.7 installé sur la machine.

c) Lancement et configuration
Pour lancer ElasticSearch, il faut lancer la commande suivante dans le terminal: %ELASTICSEARCH_HOME%/bin/elasticsearch.

Par défaut, ElasticSearch utilise le port 9200. Pour vérifier que tout s'est bien lancé, l'URL localhost:9200 permet de visualiser les informations sur le cluster (notamment le statut, le nom, la version et le tagline). Le nom du cluster et le nom du noeud peuvent être fournis en paramètre de la ligne de commande précédente pour modifier les valeurs par défaut.

Il est aussi possible d'utiliser le plugin head pour avoir une vue plus ergonomique que le document json précédemment renvoyé. Il suffit de lancer la commande suivante: %ELASTICSEARCH_HOME%/bin/plugin --install mobz/elasticsearch-head

d) API REST
ElasticSearch est livrée avec une API REST (_cat) qui permet d'avoir différentes informations sur le cluster et les noeuds:
- localhost:9200/_cat/health?v : pour l'état du cluster
- localhost:9200/_cat/nodes?v : pour la liste des noeuds du cluster
- localhost:9200/_cat/indices?v : pour la liste des indexs

Il y a également d'autres opérations qui sont réalisables via l'API telles que la création/suppression d'index, l'interrogation/modification/suppression  de documents, etc.


3- Kibana
Kibana est le client utilisé pour interroger les données stockées dans ElasticSearch par Logstash.

a) Téléchargement
La dernière version de Kibana est téléchargeable depuis http://www.elasticsearch.org/overview/elkdownloads/. La version utilisée pour ce showcase est la 3.1.0 et est disponible dans le projet sous github.

b) Pré-requis
Afin de pouvoir utiliser Kibana, il faut qu'il y ait un serveur web installé sur la machine (Apache, Tomcat, etc)

c) Lancement et configuration
Pour lancer Kibana, il faut déposer le dossier Kibana dans le répertoire web du serveur (/var/www pour Apache et %TOMCAT_HOME%/webapps pour Tomcat).
Une fois le dépôt effectué, lancer http:/localhost/kibana dans un navigateur pour découvrir l'interface graphique de Kibana. Il ne reste plus qu'à requêter nos données collectées pour les restituer sous formes de graphes.


4- Log-generator
Un projet récupéré sous Github qui permet de générer des logs de recherche et de vente de produits Hi-Tech. Il a été modifié pour rajouter la durée des traitements pour chaque requête simulée. C'est un jar qu'il faut builder et lancer comme suit:
mvn clean package
java -jar target/log-generator-0.0.1-SNAPSHOT.jar -n 10 (génère 10 lignes de logs)
java -jar target/log-generator-0.0.1-SNAPSHOT.jar -n 100 -r 1000 -t 2 (génère 100 logs toutes les secondes dans 2 threads)