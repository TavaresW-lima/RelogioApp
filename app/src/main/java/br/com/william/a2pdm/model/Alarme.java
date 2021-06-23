package br.com.william.a2pdm.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Map;

public class Alarme implements Serializable {

    private int id = 0;
    private String nome;
    private LocalTime horario;
    private boolean isActive;
    private Map<DiasSemanaEnum, Boolean> diasSemana;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Map<DiasSemanaEnum, Boolean> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(Map<DiasSemanaEnum, Boolean> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public static class AlarmeBuilder {

        private String nome;
        private LocalTime horario;
        private boolean isActive;
        private Map<DiasSemanaEnum, Boolean> diasSemana;

        public AlarmeBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public AlarmeBuilder setHorario(LocalTime horario) {
            this.horario = horario;
            return this;
        }

        public AlarmeBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public AlarmeBuilder setDiasSemana(Map<DiasSemanaEnum, Boolean> diasSemana) {
            this.diasSemana = diasSemana;
            return this;
        }

        public Alarme build() {
            Alarme alarme = new Alarme();
            alarme.setNome(this.nome);
            alarme.setHorario(this.horario);
            alarme.setDiasSemana(this.diasSemana);
            alarme.setActive(this.isActive);
            return alarme;
        }

        private AlarmeBuilder(){}

        public static AlarmeBuilder builder() {
            return new AlarmeBuilder();
        }
    }


}
