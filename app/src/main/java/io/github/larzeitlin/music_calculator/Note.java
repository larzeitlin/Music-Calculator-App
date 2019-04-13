package io.github.larzeitlin.music_calculator;

public class Note {
    private static final int semitonesInOctave = 12;
    private static final double A4hz = 440.0;
    private static final int octaveOfA4 = 4;
    private static final int positionOfAInOctave = 9;
    private static final String[] notesInOctave = {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G",  "G#/Ab", "A", "A#/Bb", "B"};

    private double hz;
    private int octave;
    private int cents;
    private int noteNumber;
    private String noteName;
    private double semitonesFromA4;
    private int roundedSemitonesFromA4;

    public Note(){
        this.hz = 440;
        this.octave = 4;
        this.cents = 0;
        this.noteNumber = positionOfAInOctave;
        this.noteName = "A";
        this.semitonesFromA4 = 0.0;
        this.roundedSemitonesFromA4 = 0;
    }

    private int noteNumberFromName(String noteName){
        int counter = 0;
        while (counter < semitonesInOctave) {
            if (noteName.equals(notesInOctave[counter])){
                return counter;
            }
            else {counter += 1;
            }
        }
        throw new IllegalArgumentException("Not a valid note name");
    }

    private double hzToSemitonesFromA4(double hz){
        double octDiff =  Math.log(hz / A4hz) / Math.log(2.0);
        return semitonesInOctave * octDiff;

    }

    private int semitonesFromA4FromNoteNumberAndOctive(int noteNumber, int octave){
        int A4AsSemitones = (semitonesInOctave * octaveOfA4) + positionOfAInOctave;
        int noteInAsSemitones = (semitonesInOctave * octave) + noteNumber;
        return noteInAsSemitones - A4AsSemitones;
    }

    private double HzFromSemitonesFromA4(double semitonesFromA4){
        return A4hz * Math.pow(2.0, (semitonesFromA4 / semitonesInOctave));
    }

    private int octiveFromSemitonesFromA4(int roundedSemitonesFromA4){
        int A4AsSemitones = (semitonesInOctave * octaveOfA4) + positionOfAInOctave;
        int noteAsSemitones = A4AsSemitones + roundedSemitonesFromA4;
        return noteAsSemitones / semitonesInOctave;

    }

    private int noteNumberFromSemitonesFromA4(int roundedSemitonesFromA4){
        int semitonesWithAOffset = positionOfAInOctave + roundedSemitonesFromA4;
        int relitiveNotePosition = semitonesWithAOffset % semitonesInOctave;
        return (relitiveNotePosition + semitonesInOctave) % semitonesInOctave;
    }

    private void calculateNote(){
        this.semitonesFromA4 = hzToSemitonesFromA4(this.hz);
        this.roundedSemitonesFromA4 = (int) Math.round(this.semitonesFromA4);
        this.cents = (int) Math.round(100 * (this.semitonesFromA4 - this.roundedSemitonesFromA4));
        this.octave =  octiveFromSemitonesFromA4(this.roundedSemitonesFromA4);
        this.noteNumber =  noteNumberFromSemitonesFromA4(this.roundedSemitonesFromA4);
        this.noteName = notesInOctave[this.noteNumber];
    }

    public void setHz(double hz) {
        this.hz = hz;
        calculateNote();
    }
    public void setOctave(int octave) {
        this.octave = octave;
        this.hz = HzFromSemitonesFromA4(this.semitonesFromA4);
        calculateNote();
    }
    public void setCents(int cents) {
        this.cents = cents;
        this.hz = HzFromSemitonesFromA4(this.semitonesFromA4);
        calculateNote();
    }
    public void setNote(String noteName, int octave) {
        this.noteName = noteName;
        this.noteNumber = noteNumberFromName(this.noteName);
        this.octave = octave;
        this.semitonesFromA4 = semitonesFromA4FromNoteNumberAndOctive(this.noteNumber, this.octave);
        this.cents = 0;
        this.hz = HzFromSemitonesFromA4(this.semitonesFromA4);
        calculateNote();
    }

    public double getHz() {
        return this.hz;
    }

    public int getOctave() {
        return this.octave;
    }

    public int getNoteNumber() {
        return this.noteNumber;
    }

    public int getCents() {
        return this.cents;
    }

    public String getNoteName() {
        return this.noteName;
    }
}
