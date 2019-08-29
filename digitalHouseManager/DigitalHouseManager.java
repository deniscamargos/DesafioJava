package br.DigitalHouse;

import java.util.ArrayList;
import java.util.List;

public class DigitalHouseManager {

    List<Aluno> listaAlunos = new ArrayList<>();
    List<Professor> listaProfessores = new ArrayList<>();
    List<Curso> listaCursos = new ArrayList<>();
    List<Matricula> listaMatriculas = new ArrayList<>();

    public void registrarCurso(String nome, Integer codigoCurso, Integer numeroVagas) {
        Curso curso = new Curso(nome, codigoCurso, numeroVagas);
        listaCursos.add(curso);

    }
    public void excluirCurso(Integer codigoCurso) {
        Curso curso = new Curso(codigoCurso);
        listaCursos.remove(codigoCurso);

    }
    public void registrarProfessorAdjunto(String nome, String sobrenome, Integer experiencia, Integer codigoProfessor){
        ProfessorAdjunto professorAdjunto = new ProfessorAdjunto (nome,  sobrenome, experiencia, codigoProfessor);
        listaProfessores.add(professorAdjunto);

    }
    public void registrarProfessorTitular(String nome, String sobrenome, String especialidade, Integer codigoProfessor){
        ProfessorTitular professorTitular = new ProfessorTitular(nome, sobrenome, codigoProfessor, especialidade);
        listaProfessores.add(professorTitular);

    }
    public void excluirProfessor(Integer codigoProfessor){
        ProfessorAdjunto professorAdjunto = new ProfessorAdjunto(codigoProfessor);
        ProfessorTitular professorTitular = new ProfessorTitular(codigoProfessor);
        listaProfessores.remove(codigoProfessor);

    }
    public void registrarAluno(String nome, String sobrenome,Integer codigoAluno){
        Aluno aluno = new Aluno(nome, sobrenome, codigoAluno);
        listaAlunos.add(aluno);
        System.out.println("Aluno foi add " + nome);
    }

    public void matricularAluno(Integer codigoAluno, Integer codigoCurso){
        Curso curso = null;
        Aluno aluno = null;
        Matricula matricula;


        for (int i = 0; i < listaCursos.size(); i++) {
            if (listaCursos.get(i).getCodigoCurso().equals(codigoCurso)) {
                curso = listaCursos.get(i);
            }
        }


        for (int y = 0; y < listaAlunos.size(); y++) {
            if (listaAlunos.get(y).getCodigoAluno().equals(codigoAluno)) {
                aluno = listaAlunos.get(y);
            }
        }
        if (curso != null && aluno != null) {
            if (curso.adicionarAlunos(aluno)) {
                matricula = new Matricula(curso, aluno);
                listaMatriculas.add(matricula);
                System.out.println("Aluno Matriculado com sucesso");
            }

        }else if (aluno == null){
            System.out.println("Aluno não encontrado");
        }else if (curso == null){
            System.out.println("Curso não encontrado");
        }
    }


    public void alocarProfessores(Integer codigoCurso, Integer codigoProfessorTitular, Integer codigoProfessorAdjunto) {
        ProfessorTitular profTitular = null;
        ProfessorAdjunto profAdjunto = null;
        Curso profCurso = null;
        for (Professor professor : listaProfessores) {
            if (professor.getCodigoProfessor().equals(codigoProfessorTitular)) {
                profTitular = (ProfessorTitular) professor;
            }
        }
        for (Professor professor : listaProfessores) {
            if (professor.getCodigoProfessor().equals(codigoProfessorAdjunto)) {
                profAdjunto = (ProfessorAdjunto) professor;
            }
        }
        for (Curso valor : listaCursos) {
            if (valor.getCodigoCurso().equals(codigoCurso)) {
                profCurso = (Curso) valor;
            }
        }
        if (profCurso != null) {
            profCurso.setProfAdjunto(profAdjunto);
            profCurso.setProfTitular(profTitular);
            System.out.println("Deu certo");
        } else {
            System.out.println("Deu ruim");
        }


    }
}
