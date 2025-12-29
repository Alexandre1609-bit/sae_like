# Java Core Network Project (Learning by Doing)

## Objectif du projet
Ce projet est une application développée en **Java pur**.
Le choix a été fait délibérément de **ne pas utiliser de frameworks** (comme Spring Boot) pour ce développement.

**L'objectif est double :**
1. Comprendre en profondeur les mécanismes internes de Java.
2. Construire une architecture propre "à la main" pour mieux saisir l'intérêt des couches d'abstraction par la suite.

## Stack Technique
* **Langage :** Java (Core)
* **Architecture :** Custom (sans Framework)

## État actuel & Limitations (Honest Disclaimer)
Ce projet est un **Work In Progress**. L'accent est mis sur l'apprentissage et l'évolution du code.

Certaines fonctionnalités sont encore instables ou en cours de débogage :
* **Problème connu :** La fonctionnalité de `join` (gestion des connexions ou fusion de données) présente des dysfonctionnements et doit être refactorisée.
* Le code est en cours de nettoyage pour respecter strictement les principes de **Clean Code**.

## Roadmap / À venir
* Correction du bug `join`.
* Refactoring pour améliorer la lisibilité et la séparation des responsabilités.
* Intégration d'un pipeline CI/CD (linter & tests).
* Intégration de Docker (docker compose) 

---
*Ce projet documente ma progression technique et ma capacité à travailler sans filet de sécurité.*