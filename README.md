Logstash + ElasticSearch + Kibana Showcase
========

Un exemple d'utilisation dela combinaison de ces trois projets compl�mentaires.

1- Logstash
Logstash est un outil de gestion d'�v�nements et de logs qui peut �tre utilis� pour collecter, parser et transformer les logs afin de les exploiter plus tard (recherche, m�triques, etc).
Il est libre et totalement open-source.

a) T�l�chargement
La derni�re version de Logstash est t�l�chargeable depuis http://logstash.net/. La version utilis�e pour ce showcase est la 1.4.2. Elle est incluse dans le showcase.

b) Pr�-requis
Afin de pouvoir utiliser Logstash, il installer au pr�alable le JDK sur la machine.

La documentation recommande d'utiliser une version r�cente pour tirer le meilleur parti de Logstash. Nous utilisons pour nos tests Java 1.7.

c) Lancement et configuration

Logstash utilise un agent pour collecter, parser et stocker les logs qui lui sont transmis. Ainsi, pour faire tourner Logstash il faut lancer l'agent en lui fournissant certains param�tres de configuration. La commande est de la forme %LOGSTASH_HOME%/bin/logstash agent -options et se lance dans un terminal.

Les options g�n�ralement utilis�es sont:

- e : indique que la configuration de Logstash est directement pass�e dans la ligne de commande  (ex: bin/logstash -e 'input { stdin { } } output { stdout { codec => rubydebug } }')
- f : indique que la configuration de Logstash est d�finir dans le fichier pass� en param�tre (ex: bin/logstash -f logstash.conf)

Les autres options sont consultables � l'adresse suivante: http://logstash.net/docs/1.4.2/flags.

d) Processus de traitement Logstash
A la r�ception d'un �venement, Logstash se base sur une chaine de composants pr�sents dans le fichier de configuration. Les composants utilis�s sont les suivants:
- input : c'est la premi�re phase du pipeline Logstash. Un input repr�sente la source des logs (m�chanisme de passage des logs � Logstash). Les inputs support�s de base par Logstash sont regroup�s dans %LOGSTASH_HOME%/lib/logstash/inputs. La liste des inputs fr�quemment utilis�s peut �tre visualis�e � http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (�Inputs)

- filter : c'est la phase interm�diaire du pipeline Logstash. Dans cette phase, on peut enchainer plusieurs filtres. Un filtre permet d'effectuer des traitements et peut �tre coupl�e avec des conditions pour effectuer certaines actions. Les filtres sont en fait des expressions r�guli�res pr�d�finies et Logstash vient avec un certain nombre de filtres qui sont regroup�s dans %LOGSTASH_HOME%/lib/logstash/filters. La liste des filtres fr�quemment utilis�s peut �tre visualis�e � http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (�Filters)

- output : c'est la derni�re phase du pipeline Logstash. Un output repr�sente la sortie vers laquelle sont redirig�s les r�sultats des traitements Logstash. Les outputs support�s de base par Logstash sont regroup�s dans %LOGSTASH_HOME%/lib/logstash/outputs. La liste des outputs fr�quemment utilis�s peut �tre visualis�e � http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (�Outputs)

- codec : c'est un filtre de flux qui peut �tre utilis� dans un input ou un output. Les codecs permettent de facilement s�parer le processus de transport des messages de celui de la s�rialisation. La liste des codecs fr�quemment utilis�s peut �tre visualis�e � http://logstash.net/docs/1.4.2/tutorials/getting-started-with-logstash (�Codecs)

En r�gle g�n�rale, un fichier de configuration Logstash contient toujours un input qui est la source des logs (peut �tre la console ou un/plusieurs fichier(s)), un ou plusieurs filtres (qui correspondent aux actions effectu�es lors du parsing des logs) et un output qui est la sortie (peut �tre la console ou ElasticSearch par exemple).


2- ElasticSearch
ElasticSearch est un moteur open-source de recherche full-text qui utilise une base de donn�es NoSQL. Il est bas� sur Luc�ne et permet de stocker, rechercher et analyser rapidement de grands volumes de donn�es quasiment en temps r�el. It allows you to store, search, and analyze big volumes of data quickly and in near real time

a) T�l�chargement
La derni�re version d'ElasticSearch est t�l�chargeable depuis http://www.elasticsearch.org/overview/elkdownloads/. La version utilis�e pour ce showcase est la 1.3.2 et est disponible dans le projet sous github.

b) Pr�-requis
Afin de pouvoir utiliser ElasticSearch, il faut qu'il y ait Java 1.7 install� sur la machine.

c) Lancement et configuration
Pour lancer ElasticSearch, il faut lancer la commande suivante dans le terminal: %ELASTICSEARCH_HOME%/bin/elasticsearch.

Par d�faut, ElasticSearch utilise le port 9200. Pour v�rifier que tout s'est bien lanc�, l'URL localhost:9200 permet de visualiser les informations sur le cluster (notamment le statut, le nom, la version et le tagline). Le nom du cluster et le nom du noeud peuvent �tre fournis en param�tre de la ligne de commande pr�c�dente pour modifier les valeurs par d�faut.

Il est aussi possible d'utiliser le plugin head pour avoir une vue plus ergonomique que le document json pr�c�demment renvoy�. Il suffit de lancer la commande suivante: %ELASTICSEARCH_HOME%/bin/plugin --install mobz/elasticsearch-head

d) API REST
ElasticSearch est livr�e avec une API REST (_cat) qui permet d'avoir diff�rentes informations sur le cluster et les noeuds:
- localhost:9200/_cat/health?v : pour l'�tat du cluster
- localhost:9200/_cat/nodes?v : pour la liste des noeuds du cluster
- localhost:9200/_cat/indices?v : pour la liste des indexs

Il y a �galement d'autres op�rations qui sont r�alisables via l'API telles que la cr�ation/suppression d'index, l'interrogation/modification/suppression  de documents, etc.


3- Kibana
Kibana est le client utilis� pour interroger les donn�es stock�es dans ElasticSearch par Logstash.

a) T�l�chargement
La derni�re version de Kibana est t�l�chargeable depuis http://www.elasticsearch.org/overview/elkdownloads/. La version utilis�e pour ce showcase est la 3.1.0 et est disponible dans le projet sous github.

b) Pr�-requis
Afin de pouvoir utiliser Kibana, il faut qu'il y ait un serveur web install� sur la machine (Apache, Tomcat, etc)

c) Lancement et configuration
Pour lancer Kibana, il faut d�poser le dossier Kibana dans le r�pertoire web du serveur (/var/www pour Apache et %TOMCAT_HOME%/webapps pour Tomcat).
Une fois le d�p�t effectu�, lancer http:/localhost/kibana dans un navigateur pour d�couvrir l'interface graphique de Kibana. Il ne reste plus qu'� requ�ter nos donn�es collect�es pour les restituer sous formes de graphes.


4- Log-generator
Un projet r�cup�r� sous Github qui permet de g�n�rer des logs de recherche et de vente de produits Hi-Tech. Il a �t� modifi� pour rajouter la dur�e des traitements pour chaque requ�te simul�e. C'est un jar qu'il faut builder et lancer comme suit:
mvn clean package
java -jar target/log-generator-0.0.1-SNAPSHOT.jar -n 10 (g�n�re 10 lignes de logs)
java -jar target/log-generator-0.0.1-SNAPSHOT.jar -n 100 -r 1000 -t 2 (g�n�re 100 logs toutes les secondes dans 2 threads)