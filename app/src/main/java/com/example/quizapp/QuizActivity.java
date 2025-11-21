package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, numberText;
    RadioGroup optionsGroup;
    RadioButton optA, optB, optC, optD;
    Button nextBtn;

    List<QuestionModel> allQuestions = new ArrayList<>();
    List<QuestionModel> quizQuestions = new ArrayList<>();

    int index = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        numberText = findViewById(R.id.numberText);
        optionsGroup = findViewById(R.id.radioGroup);

        optA = findViewById(R.id.optionA);
        optB = findViewById(R.id.optionB);
        optC = findViewById(R.id.optionC);
        optD = findViewById(R.id.optionD);

        nextBtn = findViewById(R.id.nextBtn);

        loadQuestions();   // Load all 200 questions here

        Collections.shuffle(allQuestions);
        quizQuestions = allQuestions.subList(0, 30);

        loadQuestion();

        nextBtn.setOnClickListener(view -> checkAnswer());
    }


    private void loadQuestions() {
        // ---------------- PART 1 — Questions 1 to 50 ----------------

        allQuestions.add(new QuestionModel("What does CPU stand for?",
                "Central Processing Unit", "Control Program Unit", "Central Performance Utility", "Computer Power Unit",
                "Central Processing Unit"));

        allQuestions.add(new QuestionModel("Which gate gives output HIGH only when all inputs are HIGH?",
                "OR gate", "AND gate", "NOR gate", "XOR gate",
                "AND gate"));

        allQuestions.add(new QuestionModel("Which material has the highest electrical conductivity?",
                "Copper", "Gold", "Silver", "Aluminium",
                "Silver"));

        allQuestions.add(new QuestionModel("Which law states that V = IR?",
                "Newton’s Law", "Ohm’s Law", "Kirchhoff’s Law", "Faraday’s Law",
                "Ohm’s Law"));

        allQuestions.add(new QuestionModel("What is the SI unit of force?",
                "Joule", "Pascal", "Newton", "Watt",
                "Newton"));

        allQuestions.add(new QuestionModel("Which part of a computer stores permanent instructions?",
                "RAM", "Cache", "ROM", "Register",
                "ROM"));

        allQuestions.add(new QuestionModel("Which algorithm is used to find the shortest path?",
                "Dijkstra", "Prim", "Kruskal", "DFS",
                "Dijkstra"));

        allQuestions.add(new QuestionModel("Which material is used for bulb filaments?",
                "Copper", "Iron", "Tungsten", "Gold",
                "Tungsten"));

        allQuestions.add(new QuestionModel("Which device measures electric current?",
                "Voltmeter", "Ammeter", "Wattmeter", "Energy Meter",
                "Ammeter"));

        allQuestions.add(new QuestionModel("Which process converts AC to DC?",
                "Inversion", "Rectification", "Amplification", "Modulation",
                "Rectification"));

        allQuestions.add(new QuestionModel("Which unit is used to measure frequency?",
                "Hertz", "Newton", "Joule", "Pascal",
                "Hertz"));

        allQuestions.add(new QuestionModel("Which has the highest melting point?",
                "Iron", "Platinum", "Tungsten", "Titanium",
                "Tungsten"));

        allQuestions.add(new QuestionModel("Which test checks hardness of materials?",
                "Tensile Test", "Brinell Test", "Fatigue Test", "Impact Test",
                "Brinell Test"));

        allQuestions.add(new QuestionModel("Which device stores energy in electric field?",
                "Resistor", "Inductor", "Capacitor", "Transformer",
                "Capacitor"));

        allQuestions.add(new QuestionModel("Which phenomenon explains rainbow formation?",
                "Diffraction", "Reflection", "Refraction", "Interference",
                "Refraction"));

        allQuestions.add(new QuestionModel("Which process joins metals using heat?",
                "Casting", "Machining", "Welding", "Forging",
                "Welding"));

        allQuestions.add(new QuestionModel("Which is NOT a programming language?",
                "Python", "HTML", "Java", "C++",
                "HTML"));

        allQuestions.add(new QuestionModel("Which bridge measures unknown resistance?",
                "Maxwell Bridge", "Schering Bridge", "Wheatstone Bridge", "Kelvin Bridge",
                "Wheatstone Bridge"));

        allQuestions.add(new QuestionModel("Which device converts mechanical energy into electrical energy?",
                "Transformer", "Generator", "Motor", "Inductor",
                "Generator"));

        allQuestions.add(new QuestionModel("Which sensor is used for temperature?",
                "LDR", "Thermistor", "Ultrasonic", "Proximity Sensor",
                "Thermistor"));

        allQuestions.add(new QuestionModel("What is the unit of power?",
                "Watt", "Volt", "Ampere", "Ohm",
                "Watt"));

        allQuestions.add(new QuestionModel("Which beam experiences bending?",
                "Axial beam", "Longitudinal beam", "Transverse beam", "Rotational beam",
                "Transverse beam"));

        allQuestions.add(new QuestionModel("What is the value of acceleration due to gravity?",
                "8.9 m/s²", "9.8 m/s²", "10.5 m/s²", "12 m/s²",
                "9.8 m/s²"));

        allQuestions.add(new QuestionModel("Which law explains electromagnetic induction?",
                "Ohm’s Law", "Coulomb’s Law", "Faraday’s Law", "Ampere’s Law",
                "Faraday’s Law"));

        allQuestions.add(new QuestionModel("Which device blocks DC and passes AC?",
                "Resistor", "Capacitor", "Fuse", "Inductor",
                "Capacitor"));

        allQuestions.add(new QuestionModel("Which is used during power failure?",
                "Motor", "Generator", "Circuit Breaker", "Fuse",
                "Generator"));

        allQuestions.add(new QuestionModel("Which type of motor runs on AC supply?",
                "DC Motor", "Shunt Motor", "Induction Motor", "Series Motor",
                "Induction Motor"));

        allQuestions.add(new QuestionModel("Which material is used in cement?",
                "Quartz", "Gypsum", "Mica", "Hematite",
                "Gypsum"));

        allQuestions.add(new QuestionModel("Which effect is used in loudspeakers?",
                "Hall Effect", "Piezoelectric Effect", "Electromagnetic Induction", "Thermoelectric Effect",
                "Electromagnetic Induction"));

        allQuestions.add(new QuestionModel("Which of the following is a renewable energy source?",
                "Coal", "Petrol", "Solar", "Diesel",
                "Solar"));

        allQuestions.add(new QuestionModel("Which fluid property resists flow?",
                "Viscosity", "Density", "Capillarity", "Surface Tension",
                "Viscosity"));

        allQuestions.add(new QuestionModel("Which is used to measure pressure?",
                "Thermometer", "Manometer", "Hygrometer", "Speedometer",
                "Manometer"));

        allQuestions.add(new QuestionModel("Which law states that like charges repel?",
                "Faraday’s Law", "Coulomb’s Law", "Gauss’s Law", "Lenz’s Law",
                "Coulomb’s Law"));

        allQuestions.add(new QuestionModel("Which element is used in batteries?",
                "Iron", "Lithium", "Aluminium", "Silver",
                "Lithium"));

        allQuestions.add(new QuestionModel("Which is the SI unit of electric charge?",
                "Coulomb", "Watt", "Henry", "Volt",
                "Coulomb"));

        allQuestions.add(new QuestionModel("Which machining process removes material?",
                "Casting", "Milling", "Forging", "Extrusion",
                "Milling"));

        allQuestions.add(new QuestionModel("Which device steps up or down voltage?",
                "Resistor", "Transformer", "Capacitor", "Relay",
                "Transformer"));

        allQuestions.add(new QuestionModel("What is the unit of resistance?",
                "Ampere", "Watt", "Volt", "Ohm",
                "Ohm"));

        allQuestions.add(new QuestionModel("Which law is used for heat conduction?",
                "Fourier’s Law", "Newton’s Law", "Pascal’s Law", "Bernoulli’s Law",
                "Fourier’s Law"));

        allQuestions.add(new QuestionModel("Which transistor configuration has highest gain?",
                "Common Base", "Common Collector", "Common Emitter", "Darlington",
                "Common Emitter"));

        allQuestions.add(new QuestionModel("Which device converts light into electricity?",
                "Diode", "Solar Cell", "Inductor", "Capacitor",
                "Solar Cell"));

        allQuestions.add(new QuestionModel("Which phenomenon causes echo?",
                "Refraction", "Reflection", "Diffraction", "Interference",
                "Reflection"));

        allQuestions.add(new QuestionModel("Which is the hardest natural material?",
                "Diamond", "Steel", "Silicon", "Aluminium",
                "Diamond"));

        allQuestions.add(new QuestionModel("Which law explains buoyancy?",
                "Hooke’s Law", "Archimedes’ Principle", "Bernoulli’s Law", "Boyle’s Law",
                "Archimedes’ Principle"));

        allQuestions.add(new QuestionModel("Which metal is used for electrical wiring?",
                "Lead", "Copper", "Steel", "Iron",
                "Copper"));

        allQuestions.add(new QuestionModel("Which device stores energy in magnetic field?",
                "Inductor", "Resistor", "Battery", "Fuse",
                "Inductor"));

        allQuestions.add(new QuestionModel("Which material is used for making resistors?",
                "Nichrome", "Copper", "Aluminium", "Tin",
                "Nichrome"));

        allQuestions.add(new QuestionModel("What is the resistance of an ideal conductor?",
                "Zero", "One", "Infinity", "Variable",
                "Zero"));

        allQuestions.add(new QuestionModel("Which programming language is used in Android?",
                "C", "Java", "Swift", "Ruby",
                "Java"));

        allQuestions.add(new QuestionModel("Which instrument measures humidity?",
                "Anemometer", "Hygrometer", "Barometer", "Thermometer",
                "Hygrometer"));

        // ---------------- PART 2 — Questions 51 to 100 ----------------

        allQuestions.add(new QuestionModel("Which law explains the relationship between pressure and volume of gas?",
                "Boyle’s Law", "Faraday’s Law", "Hooke’s Law", "Newton’s Law",
                "Boyle’s Law"));

        allQuestions.add(new QuestionModel("Which instrument measures atmospheric pressure?",
                "Barometer", "Manometer", "Hygrometer", "Ammeter",
                "Barometer"));

        allQuestions.add(new QuestionModel("Which of these is NOT a type of load?",
                "Point Load", "External Load", "Uniform Load", "Varying Load",
                "External Load"));

        allQuestions.add(new QuestionModel("Which device allows current in only one direction?",
                "Capacitor", "Inductor", "Diode", "Transistor",
                "Diode"));

        allQuestions.add(new QuestionModel("Which branch of engineering studies motion of objects?",
                "Thermodynamics", "Mechanics", "Fluid Dynamics", "Mechatronics",
                "Mechanics"));

        allQuestions.add(new QuestionModel("Which property of material resists deformation?",
                "Stress", "Strain", "Stiffness", "Elasticity",
                "Stiffness"));

        allQuestions.add(new QuestionModel("Which memory is volatile?",
                "RAM", "ROM", "Flash", "Hard Disk",
                "RAM"));

        allQuestions.add(new QuestionModel("Which system converts heat into mechanical work?",
                "Refrigerator", "Heat Engine", "Hydraulic Press", "Compressor",
                "Heat Engine"));

        allQuestions.add(new QuestionModel("Which metal is used for galvanization?",
                "Tin", "Zinc", "Copper", "Lead",
                "Zinc"));

        allQuestions.add(new QuestionModel("Which is NOT an AC machine?",
                "Transformer", "Alternator", "Induction Motor", "DC Motor",
                "DC Motor"));

        allQuestions.add(new QuestionModel("Which fluid has no viscosity?",
                "Ideal Fluid", "Newtonian Fluid", "Real Fluid", "Laminar Fluid",
                "Ideal Fluid"));

        allQuestions.add(new QuestionModel("Which law states that force is proportional to mass × acceleration?",
                "Newton’s First Law", "Newton’s Second Law", "Newton’s Third Law", "Hooke’s Law",
                "Newton’s Second Law"));

        allQuestions.add(new QuestionModel("Which effect produces voltage in a conductor due to magnetic field?",
                "Hall Effect", "Seebeck Effect", "Photoelectric Effect", "Thermal Effect",
                "Hall Effect"));

        allQuestions.add(new QuestionModel("Which turbine is used in hydro power plants?",
                "Gas Turbine", "Pelton Wheel", "Steam Turbine", "Propeller",
                "Pelton Wheel"));

        allQuestions.add(new QuestionModel("Which device amplifies signals?",
                "Diode", "Transistor", "Capacitor", "Resistor",
                "Transistor"));

        allQuestions.add(new QuestionModel("Which is the SI unit of frequency?",
                "Watt", "Coulomb", "Henry", "Hertz",
                "Hertz"));

        allQuestions.add(new QuestionModel("Which material is the best conductor of heat?",
                "Aluminium", "Copper", "Silver", "Glass",
                "Silver"));

        allQuestions.add(new QuestionModel("Which formula gives kinetic energy?",
                "mgh", "1/2 mv²", "mv", "m/a",
                "1/2 mv²"));

        allQuestions.add(new QuestionModel("Which energy is stored in a stretched spring?",
                "Kinetic Energy", "Thermal Energy", "Potential Energy", "Electric Energy",
                "Potential Energy"));

        allQuestions.add(new QuestionModel("Which device converts sound into electrical signals?",
                "Speaker", "Microphone", "Amplifier", "Oscillator",
                "Microphone"));

        allQuestions.add(new QuestionModel("Which metal is used in thermometers?",
                "Lead", "Mercury", "Copper", "Zinc",
                "Mercury"));

        allQuestions.add(new QuestionModel("Which of the following is an insulator?",
                "Aluminium", "Silver", "Copper", "Glass",
                "Glass"));

        allQuestions.add(new QuestionModel("Which material has highest tensile strength?",
                "Aluminium", "Steel", "Brass", "Copper",
                "Steel"));

        allQuestions.add(new QuestionModel("Which law relates stress and strain?",
                "Hooke’s Law", "Faraday’s Law", "Lenz’s Law", "Boyle’s Law",
                "Hooke’s Law"));

        allQuestions.add(new QuestionModel("What is the SI unit of magnetic field?",
                "Tesla", "Weber", "Henry", "Coulomb",
                "Tesla"));

        allQuestions.add(new QuestionModel("Which cycle is used in refrigeration?",
                "Otto Cycle", "Diesel Cycle", "Rankine Cycle", "Vapor Compression Cycle",
                "Vapor Compression Cycle"));

        allQuestions.add(new QuestionModel("Which gas is commonly used for welding?",
                "Oxygen", "Nitrogen", "Argon", "Hydrogen",
                "Argon"));

        allQuestions.add(new QuestionModel("Which instrument measures rotational speed?",
                "Tachometer", "Speedometer", "Odometer", "Hygrometer",
                "Tachometer"));

        allQuestions.add(new QuestionModel("Which metal is used in electrical fuse?",
                "Iron", "Copper", "Tin-Lead Alloy", "Aluminium",
                "Tin-Lead Alloy"));

        allQuestions.add(new QuestionModel("Which electrical quantity is measured in Henry?",
                "Resistance", "Inductance", "Capacitance", "Power",
                "Inductance"));

        allQuestions.add(new QuestionModel("Which structure carries load to the foundation?",
                "Beam", "Column", "Footing", "Slab",
                "Column"));

        allQuestions.add(new QuestionModel("Which device converts digital signal to analog?",
                "ADC", "DAC", "Modulator", "Rectifier",
                "DAC"));

        allQuestions.add(new QuestionModel("Which law governs fluid continuity?",
                "Bernoulli's Equation", "Continuity Equation", "Fourier Law", "Newton Law",
                "Continuity Equation"));

        allQuestions.add(new QuestionModel("Which type of current flows in one direction?",
                "AC", "DC", "Impulse", "Transverse",
                "DC"));

        allQuestions.add(new QuestionModel("What is the SI unit of capacitance?",
                "Ohm", "Watt", "Farad", "Henry",
                "Farad"));

        allQuestions.add(new QuestionModel("Which tool is used for measuring small lengths?",
                "Scale", "Micrometer", "Tachometer", "Anemometer",
                "Micrometer"));

        allQuestions.add(new QuestionModel("Which law states that pressure applied on a fluid is transmitted equally?",
                "Pascal’s Law", "Hooke’s Law", "Newton’s Law", "Boyle’s Law",
                "Pascal’s Law"));

        allQuestions.add(new QuestionModel("Which is NOT a semiconductor material?",
                "Silicon", "Germanium", "Gallium Arsenide", "Copper",
                "Copper"));

        allQuestions.add(new QuestionModel("Which device is used for voltage regulation?",
                "Zener Diode", "Inductor", "Resistor", "Fuse",
                "Zener Diode"));

        allQuestions.add(new QuestionModel("What is the SI unit of energy?",
                "Watt", "Joule", "Newton", "Farad",
                "Joule"));

        allQuestions.add(new QuestionModel("Which gas is used in fire extinguishers?",
                "CO₂", "O₂", "H₂", "N₂",
                "CO₂"));

        allQuestions.add(new QuestionModel("Which material shows magnetism?",
                "Wood", "Copper", "Iron", "Plastic",
                "Iron"));

        allQuestions.add(new QuestionModel("Which effect explains bending of light in different media?",
                "Refraction", "Reflection", "Diffraction", "Absorption",
                "Refraction"));

        allQuestions.add(new QuestionModel("Which metal is used to make coins?",
                "Lead", "Zinc", "Nickel", "Cadmium",
                "Nickel"));

        allQuestions.add(new QuestionModel("Which turbine is used in steam power plants?",
                "Pelton Turbine", "Francis Turbine", "Steam Turbine", "Gas Turbine",
                "Steam Turbine"));

        allQuestions.add(new QuestionModel("What is the SI unit of torque?",
                "Newton", "Joule", "Newton-meter", "Pascal",
                "Newton-meter"));

        allQuestions.add(new QuestionModel("Which law states heat flows from hot to cold?",
                "Newton’s Cooling Law", "Fourier Law", "Second Law of Thermodynamics", "First Law of Thermodynamics",
                "Second Law of Thermodynamics"));

        allQuestions.add(new QuestionModel("What is used to increase the strength of concrete?",
                "Steel Rods", "Copper Wires", "Glass", "Wood",
                "Steel Rods"));

        allQuestions.add(new QuestionModel("Which test checks impact strength?",
                "Tensile Test", "Izod Test", "Creep Test", "Hardness Test",
                "Izod Test"));

        allQuestions.add(new QuestionModel("Which instrument measures high voltage?",
                "Voltmeter", "Potentiometer", "Megger", "Oscilloscope",
                "Megger"));

        allQuestions.add(new QuestionModel("Which material is used in electrical insulation?",
                "Glass", "Copper", "Iron", "Silver",
                "Glass"));

        // ---------------- PART 3 — Questions 101 to 150 ----------------

        allQuestions.add(new QuestionModel("Which device is used to measure electric power?",
                "Ammeter", "Wattmeter", "Voltmeter", "Ohmmeter",
                "Wattmeter"));

        allQuestions.add(new QuestionModel("Which law relates voltage, current, and resistance?",
                "Newton’s Law", "Ohm’s Law", "Faraday’s Law", "Hooke’s Law",
                "Ohm’s Law"));

        allQuestions.add(new QuestionModel("Which instrument measures liquid flow rate?",
                "Venturimeter", "Thermometer", "Anemometer", "Hygrometer",
                "Venturimeter"));

        allQuestions.add(new QuestionModel("Which structure supports load in buildings?",
                "Column", "Beam", "Slab", "Footing",
                "Column"));

        allQuestions.add(new QuestionModel("Which metal is used for making aircraft bodies?",
                "Iron", "Aluminium", "Copper", "Nickel",
                "Aluminium"));

        allQuestions.add(new QuestionModel("The unit of electric field is?",
                "Newton", "Volt/meter", "Coulomb", "Tesla",
                "Volt/meter"));

        allQuestions.add(new QuestionModel("What does PWM stand for?",
                "Power Width Machine", "Pulse Width Modulation", "Pulse Wave Module", "Power Wave Mode",
                "Pulse Width Modulation"));

        allQuestions.add(new QuestionModel("Which device stores electrical charge?",
                "Resistor", "Inductor", "Capacitor", "Transformer",
                "Capacitor"));

        allQuestions.add(new QuestionModel("Which beam carries pure bending?",
                "Long Beam", "Short Beam", "Cantilever", "Simply Supported Beam",
                "Simply Supported Beam"));

        allQuestions.add(new QuestionModel("Which sensor detects light intensity?",
                "Thermistor", "LDR", "Proximity Sensor", "Ultrasonic Sensor",
                "LDR"));

        allQuestions.add(new QuestionModel("What is the SI unit of pressure?",
                "Newton", "Pascal", "Joule", "Henry",
                "Pascal"));

        allQuestions.add(new QuestionModel("Which cycle is used in petrol engines?",
                "Otto Cycle", "Diesel Cycle", "Carnot Cycle", "Rankine Cycle",
                "Otto Cycle"));

        allQuestions.add(new QuestionModel("Which device converts electrical to mechanical energy?",
                "Motor", "Generator", "Transformer", "Rectifier",
                "Motor"));

        allQuestions.add(new QuestionModel("Which is the most ductile metal?",
                "Iron", "Gold", "Copper", "Aluminium",
                "Gold"));

        allQuestions.add(new QuestionModel("Which load acts perpendicular to the surface?",
                "Axial Load", "Torsional Load", "Transverse Load", "Shear Load",
                "Transverse Load"));

        allQuestions.add(new QuestionModel("Which instrument is used to measure very high resistance?",
                "Ohmmeter", "Megger", "Ammeter", "Multimeter",
                "Megger"));

        allQuestions.add(new QuestionModel("Which gas is filled in CFL bulbs?",
                "Oxygen", "Nitrogen", "Argon", "Mercury Vapor",
                "Mercury Vapor"));

        allQuestions.add(new QuestionModel("Which electromagnetic wave has the highest frequency?",
                "Microwave", "Radio Wave", "X-ray", "Ultraviolet",
                "X-ray"));

        allQuestions.add(new QuestionModel("Which metal is used for soldering?",
                "Lead-Tin Alloy", "Steel", "Nickel", "Aluminium",
                "Lead-Tin Alloy"));

        allQuestions.add(new QuestionModel("Which law explains buoyancy?",
                "Hooke’s Law", "Archimedes’ Principle", "Newton’s Law", "Boyle’s Law",
                "Archimedes’ Principle"));

        allQuestions.add(new QuestionModel("Which of the following is used in transformers?",
                "Copper Windings", "Plastic", "Rubber", "Steel Plates",
                "Copper Windings"));

        allQuestions.add(new QuestionModel("Which fuel is used in nuclear power plants?",
                "Coal", "Uranium", "Natural Gas", "Diesel",
                "Uranium"));

        allQuestions.add(new QuestionModel("Which is used to measure voltage?",
                "Voltmeter", "Ammeter", "Ohmmeter", "Wattmeter",
                "Voltmeter"));

        allQuestions.add(new QuestionModel("Which test determines a material’s ductility?",
                "Tensile Test", "Brinell Test", "Impact Test", "Fatigue Test",
                "Tensile Test"));

        allQuestions.add(new QuestionModel("Which fluid property causes surface tension?",
                "Viscosity", "Cohesion", "Adhesion", "Density",
                "Cohesion"));

        allQuestions.add(new QuestionModel("Which motor is used in fans?",
                "DC Motor", "Universal Motor", "Induction Motor", "Stepper Motor",
                "Induction Motor"));

        allQuestions.add(new QuestionModel("Which material is used for machine bearings?",
                "Cast Iron", "Steel", "Bronze", "Lead",
                "Bronze"));

        allQuestions.add(new QuestionModel("Which effect causes EMF when conductor moves in magnetic field?",
                "Compton Effect", "Hall Effect", "Faraday’s Law", "Piezoelectric Effect",
                "Faraday’s Law"));

        allQuestions.add(new QuestionModel("Which diagram shows electric circuit?",
                "Block Diagram", "Flowchart", "Schematic Diagram", "Graph",
                "Schematic Diagram"));

        allQuestions.add(new QuestionModel("Which welding technique uses electrode and shielding gas?",
                "MIG", "Arc Welding", "Gas Welding", "TIG",
                "MIG"));

        allQuestions.add(new QuestionModel("Which structure carries bending moment?",
                "Column", "Beam", "Footing", "Slab",
                "Beam"));

        allQuestions.add(new QuestionModel("What is the main component of glass?",
                "Quartz", "Mica", "Sand", "Limestone",
                "Sand"));

        allQuestions.add(new QuestionModel("Which device generates AC?",
                "Rectifier", "Battery", "Alternator", "Inverter",
                "Alternator"));

        allQuestions.add(new QuestionModel("Which capacitor has polarity?",
                "Ceramic Capacitor", "Electrolytic Capacitor", "Mica Capacitor", "Polyester Capacitor",
                "Electrolytic Capacitor"));

        allQuestions.add(new QuestionModel("Which metal is used for making electrical cables?",
                "Iron", "Copper", "Tin", "Lead",
                "Copper"));

        allQuestions.add(new QuestionModel("Which method is used for metal casting?",
                "Forging", "Sand Casting", "Rolling", "Extrusion",
                "Sand Casting"));

        allQuestions.add(new QuestionModel("Which law of thermodynamics introduces entropy?",
                "First Law", "Second Law", "Third Law", "Zeroth Law",
                "Second Law"));

        allQuestions.add(new QuestionModel("Which bridge measures inductance?",
                "Wheatstone Bridge", "Maxwell Bridge", "Schering Bridge", "Kelvin Bridge",
                "Maxwell Bridge"));

        allQuestions.add(new QuestionModel("Which turbine is used in low-head hydroelectric plants?",
                "Pelton", "Francis", "Kaplan", "Impulse",
                "Kaplan"));

        allQuestions.add(new QuestionModel("Which device smoothens rectified voltage?",
                "Inductor", "Capacitor", "Transformer", "Fuse",
                "Capacitor"));

        allQuestions.add(new QuestionModel("Which operation increases metal hardness?",
                "Annealing", "Tempering", "Quenching", "Normalizing",
                "Quenching"));

        allQuestions.add(new QuestionModel("Which gas is used in arc welding?",
                "Oxygen", "Argon", "Helium", "Nitrogen",
                "Argon"));

        allQuestions.add(new QuestionModel("Which element is used in LEDs?",
                "Carbon", "Silicon", "Gallium Arsenide", "Copper",
                "Gallium Arsenide"));

        allQuestions.add(new QuestionModel("Which phenomenon explains sonic boom?",
                "Doppler Effect", "Superposition", "Mach Effect", "Refraction",
                "Mach Effect"));

        allQuestions.add(new QuestionModel("Which meter measures power factor?",
                "Volt-meter", "Wattmeter", "Power Factor Meter", "Ammeter",
                "Power Factor Meter"));

        allQuestions.add(new QuestionModel("Which law governs fluid energy conservation?",
                "Bernoulli’s Equation", "Pascal’s Law", "Fourier Law", "Coulomb Law",
                "Bernoulli’s Equation"));

        allQuestions.add(new QuestionModel("Which steel has highest carbon content?",
                "Mild Steel", "Medium Steel", "High-Carbon Steel", "Stainless Steel",
                "High-Carbon Steel"));

        allQuestions.add(new QuestionModel("Which furnace is used for melting steel?",
                "Cupola Furnace", "Blast Furnace", "Electric Arc Furnace", "Rotary Furnace",
                "Electric Arc Furnace"));

        allQuestions.add(new QuestionModel("Which is used to convert DC into AC?",
                "Rectifier", "Inverter", "Regulator", "Converter",
                "Inverter"));

        // ---------------- PART 4 — Questions 151 to 200 ----------------

        allQuestions.add(new QuestionModel("Which metal is used for making railway tracks?",
                "Aluminium", "Cast Iron", "Steel", "Copper",
                "Steel"));

        allQuestions.add(new QuestionModel("Which device is used for measuring temperature?",
                "Hygrometer", "Thermometer", "Manometer", "Barometer",
                "Thermometer"));

        allQuestions.add(new QuestionModel("Which process removes impurities from metal?",
                "Refining", "Casting", "Forging", "Rolling",
                "Refining"));

        allQuestions.add(new QuestionModel("Which electronic component opposes current flow?",
                "Inductor", "Capacitor", "Resistor", "Transformer",
                "Resistor"));

        allQuestions.add(new QuestionModel("Which gas is used in gas welding?",
                "Oxygen", "Hydrogen", "Acetylene", "Nitrogen",
                "Acetylene"));

        allQuestions.add(new QuestionModel("Which device displays AC waveform?",
                "LED", "Tachometer", "Oscilloscope", "Voltmeter",
                "Oscilloscope"));

        allQuestions.add(new QuestionModel("Which instrument is used for precise angle measurement?",
                "Protractor", "Vernier Bevel Protractor", "Micrometer", "Dial Gauge",
                "Vernier Bevel Protractor"));

        allQuestions.add(new QuestionModel("Which law states line current equals sum of branch currents?",
                "Kirchhoff’s Voltage Law", "Kirchhoff’s Current Law", "Ohm’s Law", "Faraday’s Law",
                "Kirchhoff’s Current Law"));

        allQuestions.add(new QuestionModel("Which material is used for making cutting tools?",
                "High Carbon Steel", "High Speed Steel", "Mild Steel", "Copper",
                "High Speed Steel"));

        allQuestions.add(new QuestionModel("Which process converts solid directly to gas?",
                "Evaporation", "Sublimation", "Condensation", "Melting",
                "Sublimation"));

        allQuestions.add(new QuestionModel("Which sensor detects motion?",
                "Thermistor", "PIR Sensor", "LDR", "IR LED",
                "PIR Sensor"));

        allQuestions.add(new QuestionModel("What is the SI unit of heat?",
                "Calorie", "Joule", "Watt", "Newton",
                "Joule"));

        allQuestions.add(new QuestionModel("Which motor has variable speed?",
                "Synchronous Motor", "DC Motor", "Induction Motor", "Universal Motor",
                "DC Motor"));

        allQuestions.add(new QuestionModel("Which material is most corrosion-resistant?",
                "Mild Steel", "Cast Iron", "Stainless Steel", "Aluminium",
                "Stainless Steel"));

        allQuestions.add(new QuestionModel("Which power plant has highest efficiency?",
                "Hydroelectric", "Steam", "Nuclear", "Diesel",
                "Hydroelectric"));

        allQuestions.add(new QuestionModel("Which phenomenon is used in fiber optics?",
                "Diffraction", "Interference", "Total Internal Reflection", "Diffusion",
                "Total Internal Reflection"));

        allQuestions.add(new QuestionModel("Which structure resists vertical loads?",
                "Beam", "Column", "Slab", "Footing",
                "Column"));

        allQuestions.add(new QuestionModel("Which cycle is used in steam power plants?",
                "Otto Cycle", "Rankine Cycle", "Diesel Cycle", "Brayton Cycle",
                "Rankine Cycle"));

        allQuestions.add(new QuestionModel("Which device stores programming instructions?",
                "RAM", "ROM", "Cache", "Register",
                "ROM"));

        allQuestions.add(new QuestionModel("Which fuel is used in rockets?",
                "Petrol", "Diesel", "Liquid Oxygen + Hydrogen", "Natural Gas",
                "Liquid Oxygen + Hydrogen"));

        allQuestions.add(new QuestionModel("Which device is used to measure resistance?",
                "Ammeter", "Voltmeter", "Ohmmeter", "Tachometer",
                "Ohmmeter"));

        allQuestions.add(new QuestionModel("Which material has the highest thermal conductivity?",
                "Wood", "Glass", "Copper", "Rubber",
                "Copper"));

        allQuestions.add(new QuestionModel("Which law deals with gas expansion at constant pressure?",
                "Charles’ Law", "Boyle’s Law", "Gay-Lussac Law", "Avogadro’s Law",
                "Charles’ Law"));

        allQuestions.add(new QuestionModel("What is the SI unit of electric power?",
                "Volt", "Watt", "Ampere", "Joule",
                "Watt"));

        allQuestions.add(new QuestionModel("Which process changes AC to DC?",
                "Inversion", "Rectification", "Regulation", "Amplification",
                "Rectification"));

        allQuestions.add(new QuestionModel("Which material is used in resistance wire?",
                "Copper", "Nichrome", "Aluminium", "Silver",
                "Nichrome"));

        allQuestions.add(new QuestionModel("Which device senses humidity?",
                "Hygrometer", "Thermometer", "Altimeter", "LDR",
                "Hygrometer"));

        allQuestions.add(new QuestionModel("Which gas is used in fluorescent lamps?",
                "Neon", "Argon", "Xenon", "Oxygen",
                "Argon"));

        allQuestions.add(new QuestionModel("Which mechanical device lifts heavy loads?",
                "Pulley", "Bearing", "Gear", "Flywheel",
                "Pulley"));

        allQuestions.add(new QuestionModel("Which transformer increases voltage?",
                "Step-Down", "Step-Up", "Auto Transformer", "Isolation Transformer",
                "Step-Up"));

        allQuestions.add(new QuestionModel("Which is a soft magnetic material?",
                "Ferrite", "Silicon Steel", "Cast Iron", "Copper",
                "Silicon Steel"));

        allQuestions.add(new QuestionModel("Which engine uses compression ignition?",
                "Petrol Engine", "Diesel Engine", "Gas Engine", "Jet Engine",
                "Diesel Engine"));

        allQuestions.add(new QuestionModel("Which phenomenon creates lift in aircraft?",
                "Pascal’s Law", "Bernoulli’s Principle", "Hooke’s Law", "Newton’s Third Law",
                "Bernoulli’s Principle"));

        allQuestions.add(new QuestionModel("Which device measures rotational speed?",
                "Speedometer", "Tachometer", "Anemometer", "Voltmeter",
                "Tachometer"));

        allQuestions.add(new QuestionModel("Which material is used in electrical fuses?",
                "Iron", "Tin-Lead Alloy", "Copper", "Steel",
                "Tin-Lead Alloy"));

        allQuestions.add(new QuestionModel("Which law explains magnetic force between currents?",
                "Faraday’s Law", "Ampere’s Law", "Lenz’s Law", "Coulomb’s Law",
                "Ampere’s Law"));

        allQuestions.add(new QuestionModel("Which semiconductor device emits light?",
                "Diode", "LED", "BJT", "SCR",
                "LED"));

        allQuestions.add(new QuestionModel("Which type of pump is used in hydraulic systems?",
                "Centrifugal Pump", "Gear Pump", "Diaphragm Pump", "Reciprocating Pump",
                "Gear Pump"));

        allQuestions.add(new QuestionModel("Which unit measures angular velocity?",
                "Rad/s", "m/s", "N/m", "Watt",
                "Rad/s"));

        allQuestions.add(new QuestionModel("Which electronic device stores data temporarily?",
                "ROM", "Flash", "RAM", "Hard Disk",
                "RAM"));

        allQuestions.add(new QuestionModel("Which material is used for resistor coating?",
                "Carbon", "Plastic", "Rubber", "Glass",
                "Carbon"));

        allQuestions.add(new QuestionModel("What is the SI unit of magnetic flux?",
                "Tesla", "Weber", "Henry", "Farad",
                "Weber"));

        allQuestions.add(new QuestionModel("Which welding process uses tungsten electrode?",
                "MIG", "TIG", "Arc Welding", "Gas Welding",
                "TIG"));

        allQuestions.add(new QuestionModel("Which property of fluid resists shear stress?",
                "Viscosity", "Density", "Pressure", "Elasticity",
                "Viscosity"));

        allQuestions.add(new QuestionModel("Which law explains induced EMF direction?",
                "Coulomb’s Law", "Lenz’s Law", "Faraday’s Law", "Gauss’s Law",
                "Lenz’s Law"));

        allQuestions.add(new QuestionModel("Which element is used as semiconductor?",
                "Gold", "Silicon", "Copper", "Iron",
                "Silicon"));

        allQuestions.add(new QuestionModel("Which process improves surface finish?",
                "Drilling", "Grinding", "Casting", "Forging",
                "Grinding"));

        allQuestions.add(new QuestionModel("Which device controls AC voltage?",
                "Transformer", "Resistor", "SCR", "Inductor",
                "SCR"));

        allQuestions.add(new QuestionModel("Which device produces mechanical vibrations?",
                "Oscillator", "Speaker", "Microphone", "Amplifier",
                "Speaker"));

        allQuestions.add(new QuestionModel("Which method produces strongest joints?",
                "Bolting", "Riveting", "Welding", "Brazing",
                "Welding"));

        allQuestions.add(new QuestionModel("Which device measures small lengths accurately?",
                "Scale", "Vernier Caliper", "Protractor", "Measuring Tape",
                "Vernier Caliper"));

        allQuestions.add(new QuestionModel("Which turbine is used in gas power plants?",
                "Steam Turbine", "Gas Turbine", "Francis Turbine", "Kaplan Turbine",
                "Gas Turbine"));

    }

    private void loadQuestion() {
        QuestionModel q = quizQuestions.get(index);

        numberText.setText("Question " + (index + 1) + "/30");

        questionText.setText(q.getQuestion());
        optA.setText(q.getOptionA());
        optB.setText(q.getOptionB());
        optC.setText(q.getOptionC());
        optD.setText(q.getOptionD());
    }

    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selected = findViewById(selectedId);

        if (selected.getText().toString().equals(quizQuestions.get(index).getCorrectAns())) {
            score++;
        }

        index++;

        if (index < 30) {
            optionsGroup.clearCheck();
            loadQuestion();
        } else {
            Intent i = new Intent(QuizActivity.this, ResultActivity.class);
            i.putExtra("score", score);
            startActivity(i);
            finish();
        }
    }
}
