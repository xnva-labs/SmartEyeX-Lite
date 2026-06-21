package com.smarteyex.lite

import kotlin.math.*

class MathEngine {
    
    // ============================================
    // 1. ARITMATIKA DASAR (8 fungsi)
    // ============================================
    
    fun add(a: Double, b: Double): Double = a + b
    fun subtract(a: Double, b: Double): Double = a - b
    fun multiply(a: Double, b: Double): Double = a * b
    
    fun divide(a: Double, b: Double): Double {
        require(b != 0.0) { "Pembagian dengan nol tidak diperbolehkan" }
        return a / b
    }
    
    fun modulus(a: Int, b: Int): Int {
        require(b != 0) { "Modulus dengan nol tidak diperbolehkan" }
        return a % b
    }
    
    fun power(base: Double, exp: Double): Double = base.pow(exp)
    fun sqrt(value: Double): Double = sqrt(value)
    
    fun factorial(n: Int): Long {
        require(n >= 0) { "Faktorial hanya untuk bilangan non-negatif" }
        var result = 1L
        for (i in 2..n) result *= i
        return result
    }
    
    // ============================================
    // 2. GEOMETRI 2D (14 fungsi)
    // ============================================
    
    // Persegi
    fun squareArea(side: Double): Double = side * side
    fun squarePerimeter(side: Double): Double = 4 * side
    
    // Persegi Panjang
    fun rectangleArea(length: Double, width: Double): Double = length * width
    fun rectanglePerimeter(length: Double, width: Double): Double = 2 * (length + width)
    
    // Segitiga
    fun triangleArea(base: Double, height: Double): Double = 0.5 * base * height
    fun trianglePerimeter(a: Double, b: Double, c: Double): Double = a + b + c
    
    // Lingkaran
    fun circleArea(radius: Double): Double = PI * radius * radius
    fun circleCircumference(radius: Double): Double = 2 * PI * radius
    
    // Jajar Genjang
    fun parallelogramArea(base: Double, height: Double): Double = base * height
    
    // Trapesium
    fun trapezoidArea(a: Double, b: Double, height: Double): Double = 0.5 * (a + b) * height
    
    // Belah Ketupat
    fun rhombusArea(d1: Double, d2: Double): Double = 0.5 * d1 * d2
    
    // Layang-layang
    fun kiteArea(d1: Double, d2: Double): Double = 0.5 * d1 * d2
    
    // Ellips
    fun ellipseArea(a: Double, b: Double): Double = PI * a * b
    
    // Segi-n Beraturan
    fun regularPolygonArea(sides: Int, sideLength: Double): Double {
        require(sides >= 3) { "Segi-n minimal 3 sisi" }
        return (sides * sideLength * sideLength) / (4 * tan(PI / sides))
    }
    
    // ============================================
    // 3. GEOMETRI 3D (12 fungsi)
    // ============================================
    
    // Kubus
    fun cubeVolume(side: Double): Double = side * side * side
    fun cubeSurfaceArea(side: Double): Double = 6 * side * side
    
    // Balok
    fun blockVolume(l: Double, w: Double, h: Double): Double = l * w * h
    fun blockSurfaceArea(l: Double, w: Double, h: Double): Double = 2 * (l*w + l*h + w*h)
    
    // Tabung
    fun cylinderVolume(r: Double, h: Double): Double = PI * r * r * h
    fun cylinderSurfaceArea(r: Double, h: Double): Double = 2 * PI * r * (r + h)
    
    // Kerucut
    fun coneVolume(r: Double, h: Double): Double = (1.0/3.0) * PI * r * r * h
    fun coneSurfaceArea(r: Double, h: Double): Double {
        val slant = sqrt(r*r + h*h)
        return PI * r * (r + slant)
    }
    
    // Bola
    fun sphereVolume(r: Double): Double = (4.0/3.0) * PI * r * r * r
    fun sphereSurfaceArea(r: Double): Double = 4 * PI * r * r
    
    // Limas
    fun pyramidVolume(baseArea: Double, h: Double): Double = (1.0/3.0) * baseArea * h
    
    // Torus
    fun torusVolume(R: Double, r: Double): Double = 2 * PI * PI * R * r * r
    
    // ============================================
    // 4. TRIGONOMETRI (13 fungsi)
    // ============================================
    
    fun toRadians(deg: Double): Double = Math.toRadians(deg)
    fun toDegrees(rad: Double): Double = Math.toDegrees(rad)
    
    fun sinDeg(deg: Double): Double = sin(toRadians(deg))
    fun cosDeg(deg: Double): Double = cos(toRadians(deg))
    fun tanDeg(deg: Double): Double = tan(toRadians(deg))
    
    fun cscDeg(deg: Double): Double {
        val s = sinDeg(deg)
        require(s != 0.0) { "Cosecan tidak terdefinisi untuk sudut 0° atau 180°" }
        return 1.0 / s
    }
    
    fun secDeg(deg: Double): Double {
        val c = cosDeg(deg)
        require(c != 0.0) { "Secan tidak terdefinisi untuk sudut 90° atau 270°" }
        return 1.0 / c
    }
    
    fun cotDeg(deg: Double): Double {
        val t = tanDeg(deg)
        require(t != 0.0) { "Cotangen tidak terdefinisi untuk sudut 0° atau 180°" }
        return 1.0 / t
    }
    
    fun arcsinDeg(value: Double): Double {
        require(value in -1.0..1.0) { "Arcsin hanya untuk nilai -1 sampai 1" }
        return toDegrees(asin(value))
    }
    
    fun arccosDeg(value: Double): Double {
        require(value in -1.0..1.0) { "Arccos hanya untuk nilai -1 sampai 1" }
        return toDegrees(acos(value))
    }
    
    fun arctanDeg(value: Double): Double = toDegrees(atan(value))
    
    // Hukum Sinus: a/sinA = b/sinB → mencari b
    fun lawOfSines(a: Double, angleA: Double, angleB: Double): Double {
        return a * sinDeg(angleB) / sinDeg(angleA)
    }
    
    // Hukum Cosinus: c² = a² + b² - 2ab·cos(C) → mencari c
    fun lawOfCosines(a: Double, b: Double, angleC: Double): Double {
        return sqrt(a*a + b*b - 2*a*b*cosDeg(angleC))
    }
    
    // ============================================
    // 5. STATISTIKA (12 fungsi)
    // ============================================
    
    fun mean(values: List<Double>): Double {
        require(values.isNotEmpty()) { "List tidak boleh kosong" }
        return values.sum() / values.size
    }
    
    fun median(values: List<Double>): Double {
        require(values.isNotEmpty()) { "List tidak boleh kosong" }
        val sorted = values.sorted()
        val mid = sorted.size / 2
        return if (sorted.size % 2 == 0) (sorted[mid-1] + sorted[mid]) / 2.0 else sorted[mid]
    }
    
    fun mode(values: List<Double>): List<Double> {
        require(values.isNotEmpty()) { "List tidak boleh kosong" }
        val freq = values.groupingBy { it }.eachCount()
        val maxFreq = freq.values.maxOrNull() ?: return emptyList()
        return freq.filter { it.value == maxFreq }.keys.toList()
    }
    
    fun range(values: List<Double>): Double {
        require(values.isNotEmpty()) { "List tidak boleh kosong" }
        return values.maxOrNull()!! - values.minOrNull()!!
    }
    
    fun variance(values: List<Double>): Double {
        require(values.size >= 2) { "Minimal 2 data untuk variansi" }
        val m = mean(values)
        return values.sumOf { (it - m).pow(2) } / (values.size - 1)
    }
    
    fun stdDeviation(values: List<Double>): Double = sqrt(variance(values))
    
    fun quartile(values: List<Double>, q: Int): Double {
        require(q in 1..3) { "Quartile harus 1, 2, atau 3" }
        require(values.isNotEmpty()) { "List tidak boleh kosong" }
        val sorted = values.sorted()
        return when(q) {
            1 -> median(sorted.subList(0, sorted.size/2))
            2 -> median(sorted)
            3 -> median(sorted.subList((sorted.size+1)/2, sorted.size))
            else -> 0.0
        }
    }
    
    fun percentile(values: List<Double>, p: Double): Double {
        require(p in 0.0..100.0) { "Persentil 0-100" }
        require(values.isNotEmpty()) { "List tidak boleh kosong" }
        val sorted = values.sorted()
        val index = (p/100.0) * (sorted.size - 1)
        val lower = sorted[index.toInt()]
        val upper = sorted[min(index.toInt()+1, sorted.size-1)]
        val fraction = index - index.toInt()
        return lower + fraction * (upper - lower)
    }
    
    fun iqr(values: List<Double>): Double = quartile(values, 3) - quartile(values, 1)
    
    fun zScore(value: Double, mean: Double, stdDev: Double): Double {
        require(stdDev != 0.0) { "Standar deviasi tidak boleh nol" }
        return (value - mean) / stdDev
    }
    
    fun linearRegression(x: List<Double>, y: List<Double>): Pair<Double, Double> {
        require(x.size == y.size) { "Jumlah data x dan y harus sama" }
        require(x.size >= 2) { "Minimal 2 data" }
        val n = x.size.toDouble()
        val sumX = x.sum()
        val sumY = y.sum()
        val sumXY = x.zip(y).sumOf { it.first * it.second }
        val sumX2 = x.sumOf { it * it }
        val slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)
        val intercept = (sumY - slope * sumX) / n
        return Pair(slope, intercept)
    }
    
    fun pearsonCorrelation(x: List<Double>, y: List<Double>): Double {
        require(x.size == y.size) { "Jumlah data x dan y harus sama" }
        require(x.size >= 2) { "Minimal 2 data" }
        val n = x.size.toDouble()
        val sumX = x.sum(); val sumY = y.sum()
        val sumXY = x.zip(y).sumOf { it.first * it.second }
        val sumX2 = x.sumOf { it * it }; val sumY2 = y.sumOf { it * it }
        val numerator = n * sumXY - sumX * sumY
        val denominator = sqrt((n*sumX2 - sumX*sumX) * (n*sumY2 - sumY*sumY))
        return if (denominator == 0.0) 0.0 else numerator / denominator
    }
    
    // ============================================
    // 6. PELUANG & KOMBINATORIKA (6 fungsi)
    // ============================================
    
    fun permutation(n: Int, r: Int): Long {
        require(n >= r && r >= 0) { "n harus >= r >= 0" }
        var result = 1L
        for (i in (n-r+1)..n) result *= i
        return result
    }
    
    fun combination(n: Int, r: Int): Long {
        require(n >= r && r >= 0) { "n harus >= r >= 0" }
        if (r > n/2) return combination(n, n-r)
        return permutation(n, r) / factorial(r)
    }
    
    fun probability(favorable: Int, total: Int): Double {
        require(total > 0) { "Total harus > 0" }
        return favorable.toDouble() / total
    }
    
    fun normalPDF(x: Double, mean: Double, stdDev: Double): Double {
        require(stdDev > 0) { "StdDev harus > 0" }
        val exponent = -0.5 * ((x - mean) / stdDev).pow(2)
        return (1.0 / (stdDev * sqrt(2*PI))) * exp(exponent)
    }
    
    fun binomialProbability(n: Int, k: Int, p: Double): Double {
        require(p in 0.0..1.0) { "Peluang p harus 0-1" }
        return combination(n, k).toDouble() * p.pow(k) * (1-p).pow(n-k)
    }
    
    fun expectedValue(values: List<Double>, probabilities: List<Double>): Double {
        require(values.size == probabilities.size) { "Jumlah nilai dan peluang harus sama" }
        return values.zip(probabilities).sumOf { it.first * it.second }
    }
    
    // ============================================
    // 7. KONVERSI SATUAN (21 fungsi)
    // ============================================
    
    // Panjang
    fun mToKm(m: Double): Double = m / 1000.0
    fun kmToM(km: Double): Double = km * 1000.0
    fun mToCm(m: Double): Double = m * 100.0
    fun mToMm(m: Double): Double = m * 1000.0
    fun mToInch(m: Double): Double = m * 39.37008
    fun inchToMm(inch: Double): Double = inch * 25.4
    fun mmToInch(mm: Double): Double = mm / 25.4
    fun mToFeet(m: Double): Double = m * 3.28084
    fun feetToM(feet: Double): Double = feet / 3.28084
    
    // Massa
    fun kgToG(kg: Double): Double = kg * 1000.0
    fun kgToLb(kg: Double): Double = kg * 2.20462
    fun lbToKg(lb: Double): Double = lb / 2.20462
    
    // Suhu
    fun celsiusToFahrenheit(c: Double): Double = c * 9.0/5.0 + 32.0
    fun fahrenheitToCelsius(f: Double): Double = (f - 32.0) * 5.0/9.0
    fun celsiusToKelvin(c: Double): Double = c + 273.15
    fun kelvinToCelsius(k: Double): Double = k - 273.15
    
    // Sudut
    fun degToRad(deg: Double): Double = Math.toRadians(deg)
    fun radToDeg(rad: Double): Double = Math.toDegrees(rad)
    fun rpmToRadPerSec(rpm: Double): Double = rpm * 2.0 * PI / 60.0
    
    // Gaya
    fun nToKgf(n: Double): Double = n / 9.80665
    
    // Tekanan
    fun paToBar(pa: Double): Double = pa / 100000.0
    fun paToPsi(pa: Double): Double = pa / 6894.757
    
    // Energi
    fun jouleToKwh(j: Double): Double = j / 3600000.0
    fun kwhToJoule(kwh: Double): Double = kwh * 3600000.0
    
    // ============================================
    // 8. RUMUS TEKNIK & BENGKEL (16 fungsi)
    // ============================================
    
    /** Kecepatan potong mesin bubut (m/min) */
    fun cuttingSpeed(diaMm: Double, rpm: Double): Double = PI * diaMm * rpm / 1000.0
    
    /** Feed rate (mm/min) */
    fun feedRate(rpm: Double, feedPerRev: Double): Double = rpm * feedPerRev
    
    /** Torsi (Nm) */
    fun torque(forceN: Double, radiusM: Double): Double = forceN * radiusM
    
    /** Daya motor listrik (Watt) */
    fun motorPower(voltage: Double, current: Double, efficiency: Double = 0.85): Double {
        require(efficiency in 0.0..1.0) { "Efisiensi 0-1" }
        return voltage * current * efficiency
    }
    
    /** Rasio gear */
    fun gearRatio(teethDriven: Int, teethDriver: Int): Double {
        require(teethDriver > 0) { "Gigi penggerak harus > 0" }
        return teethDriven.toDouble() / teethDriver
    }
    
    /** Tekanan hidrolik (Pa) */
    fun hydraulicPressure(forceN: Double, areaM2: Double): Double {
        require(areaM2 > 0) { "Luas harus > 0" }
        return forceN / areaM2
    }
    
    /** Gaya apung Archimedes (N) */
    fun buoyantForce(fluidDensity: Double, volumeM3: Double): Double = fluidDensity * 9.80665 * volumeM3
    
    /** Hukum Ohm: menghitung arus (A) */
    fun ohmsLaw(voltage: Double, resistance: Double): Double {
        require(resistance > 0) { "Hambatan harus > 0" }
        return voltage / resistance
    }
    
    /** Daya listrik (Watt) */
    fun electricPower(voltage: Double, current: Double): Double = voltage * current
    
    /** Energi kalor (Joule) */
    fun heatEnergy(massKg: Double, specificHeat: Double, deltaT: Double): Double = massKg * specificHeat * deltaT
    
    /** Pemuaian panjang (m) */
    fun thermalExpansion(length: Double, coeff: Double, deltaT: Double): Double = length * coeff * deltaT
    
    /** Debit fluida (m³/s) */
    fun flowRate(areaM2: Double, velocityMps: Double): Double = areaM2 * velocityMps
    
    /** Reynolds Number */
    fun reynoldsNumber(density: Double, velocity: Double, length: Double, viscosity: Double): Double {
        require(viscosity > 0) { "Viskositas harus > 0" }
        return density * velocity * length / viscosity
    }
    
    /** Hukum Hooke: gaya pegas (N) */
    fun hookesLaw(springConstant: Double, displacement: Double): Double = springConstant * displacement
    
    /** Bending stress (Pa) */
    fun bendingStress(moment: Double, distance: Double, moi: Double): Double {
        require(moi > 0) { "Momen inersia harus > 0" }
        return moment * distance / moi
    }
    
    /** Safety factor */
    fun safetyFactor(ultimateStrength: Double, workingStress: Double): Double {
        require(workingStress > 0) { "Tegangan kerja harus > 0" }
        return ultimateStrength / workingStress
    }
    
    // ============================================
    // 9. ALJABAR & KALKULUS (11 fungsi)
    // ============================================
    
    /** Persamaan linear: ax + b = c → x */
    fun solveLinear(a: Double, b: Double, c: Double): Double {
        require(a != 0.0) { "Koefisien a tidak boleh nol" }
        return (c - b) / a
    }
    
    /** Persamaan kuadrat: ax² + bx + c = 0 → x1, x2 */
    fun solveQuadratic(a: Double, b: Double, c: Double): Pair<Double, Double> {
        require(a != 0.0) { "Koefisien a tidak boleh nol" }
        val discriminant = b*b - 4*a*c
        require(discriminant >= 0) { "Diskriminan negatif, akar imajiner" }
        val sqrtD = sqrt(discriminant)
        return Pair((-b + sqrtD)/(2*a), (-b - sqrtD)/(2*a))
    }
    
    /** Sistem persamaan linear 2 variabel */
    fun solveLinearSystem(a1: Double, b1: Double, c1: Double, a2: Double, b2: Double, c2: Double): Pair<Double, Double> {
        val det = a1*b2 - a2*b1
        require(det != 0.0) { "Determinan nol, tidak ada solusi unik" }
        val x = (c1*b2 - c2*b1) / det
        val y = (a1*c2 - a2*c1) / det
        return Pair(x, y)
    }
    
    /** Logaritma dengan basis tertentu */
    fun logarithm(value: Double, base: Double): Double {
        require(value > 0 && base > 0 && base != 1.0) { "Value > 0, base > 0, base != 1" }
        return ln(value) / ln(base)
    }
    
    fun naturalLog(value: Double): Double {
        require(value > 0) { "Value harus > 0" }
        return ln(value)
    }
    
    fun exponential(x: Double): Double = exp(x)
    
    /** Deret aritmatika: Sn = n/2(2a + (n-1)d) */
    fun arithmeticSeriesSum(a: Double, d: Double, n: Int): Double {
        require(n > 0) { "n harus > 0" }
        return n/2.0 * (2*a + (n-1)*d)
    }
    
    /** Deret geometri: Sn = a(1-rⁿ)/(1-r) */
    fun geometricSeriesSum(a: Double, r: Double, n: Int): Double {
        require(n > 0) { "n harus > 0" }
        return if (r == 1.0) a*n else a*(1 - r.pow(n))/(1 - r)
    }
    
    /** Turunan polinomial di titik x */
    fun derivativePolynomial(coefficients: List<Double>, x: Double): Double {
        require(coefficients.size >= 2) { "Minimal pangkat 1" }
        var result = 0.0
        for (i in 1 until coefficients.size) {
            result += i * coefficients[i] * x.pow(i-1)
        }
        return result
    }
    
    /** Integral polinomial dari a ke b */
    fun integralPolynomial(coefficients: List<Double>, a: Double, b: Double): Double {
        fun antiDerivative(x: Double): Double {
            var result = 0.0
            for (i in coefficients.indices) {
                result += coefficients[i] * x.pow(i+1) / (i+1)
            }
            return result
        }
        return antiDerivative(b) - antiDerivative(a)
    }
    
    /** Interpolasi linear */
    fun interpolateLinear(x0: Double, y0: Double, x1: Double, y1: Double, x: Double): Double {
        require(x0 != x1) { "x0 dan x1 tidak boleh sama" }
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0)
    }
    
    // ============================================
    // 10. MATEMATIKA DISKRIT & AI (19 fungsi)
    // ============================================
    
    fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2 || n == 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i+2) == 0) return false
            i += 6
        }
        return true
    }
    
    fun nextPrime(n: Int): Int {
        var num = n + 1
        while (!isPrime(num)) num++
        return num
    }
    
    fun gcd(a: Int, b: Int): Int {
        var x = abs(a)
        var y = abs(b)
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }
    
    fun lcm(a: Int, b: Int): Int {
        require(a != 0 && b != 0) { "Tidak boleh nol" }
        return abs(a * b) / gcd(a, b)
    }
    
    fun decimalToBinary(n: Int): String = Integer.toBinaryString(n)
    fun binaryToDecimal(binary: String): Int = Integer.parseInt(binary, 2)
    
    // Fungsi Aktivasi AI
    fun sigmoid(x: Double): Double = 1.0 / (1.0 + exp(-x))
    fun relu(x: Double): Double = max(0.0, x)
    fun tanh(x: Double): Double = tanh(x)
    
    fun softmax(values: List<Double>): List<Double> {
        val maxVal = values.maxOrNull() ?: 0.0
        val expValues = values.map { exp(it - maxVal) }
        val sumExp = expValues.sum()
        return expValues.map { it / sumExp }
    }
    
    fun normalizeMinMax(values: List<Double>): List<Double> {
        val min = values.minOrNull() ?: 0.0
        val max = values.maxOrNull() ?: 1.0
        if (min == max) return values.map { 0.5 }
        return values.map { (it - min) / (max - min) }
    }
    
    fun normalizeZScore(values: List<Double>): List<Double> {
        val m = mean(values)
        val sd = stdDeviation(values)
        if (sd == 0.0) return values.map { 0.0 }
        return values.map { (it - m) / sd }
    }
    
    fun euclideanDistance(a: List<Double>, b: List<Double>): Double {
        require(a.size == b.size) { "Dimensi harus sama" }
        return sqrt(a.zip(b).sumOf { (it.first - it.second).pow(2) })
    }
    
    fun cosineSimilarity(a: List<Double>, b: List<Double>): Double {
        require(a.size == b.size) { "Dimensi harus sama" }
        val dot = a.zip(b).sumOf { it.first * it.second }
        val normA = sqrt(a.sumOf { it.pow(2) })
        val normB = sqrt(b.sumOf { it.pow(2) })
        if (normA == 0.0 || normB == 0.0) return 0.0
        return dot / (normA * normB)
    }
    
    fun accuracy(tp: Int, tn: Int, fp: Int, fn: Int): Double {
        val total = tp + tn + fp + fn
        return if (total == 0) 0.0 else (tp + tn).toDouble() / total
    }
    
    fun precision(tp: Int, fp: Int): Double {
        val denom = tp + fp
        return if (denom == 0) 0.0 else tp.toDouble() / denom
    }
    
    fun recall(tp: Int, fn: Int): Double {
        val denom = tp + fn
        return if (denom == 0) 0.0 else tp.toDouble() / denom
    }
    
    fun f1Score(precision: Double, recall: Double): Double {
        val denom = precision + recall
        return if (denom == 0.0) 0.0 else 2 * precision * recall / denom
    }
    
    fun entropy(probabilities: List<Double>): Double {
        return -probabilities.sumOf { if (it > 0) it * log2(it) else 0.0 }
    }
    
    // ========== UTILITY ==========
    
    fun format(value: Double, decimals: Int): String {
        return "%.${decimals}f".format(value)
    }
    
    fun isWholeNumber(value: Double): Boolean = value == value.toLong().toDouble()
    
    /** Kalkulator ekspresi sederhana */
    fun calculate(expression: String): Double {
        val clean = expression.replace(" ", "")
        return when {
            clean.contains("+") -> {
                val parts = clean.split("+")
                parts[0].toDouble() + parts[1].toDouble()
            }
            clean.contains("-") && clean.lastIndexOf("-") > 0 -> {
                val idx = clean.lastIndexOf("-")
                clean.substring(0, idx).toDouble() - clean.substring(idx+1).toDouble()
            }
            clean.contains("*") -> {
                val parts = clean.split("*")
                parts[0].toDouble() * parts[1].toDouble()
            }
            clean.contains("/") -> {
                val parts = clean.split("/")
                divide(parts[0].toDouble(), parts[1].toDouble())
            }
            else -> clean.toDouble()
        }
    }
}