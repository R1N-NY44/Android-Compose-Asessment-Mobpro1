package org.d3if3062.asessment1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


enum class AnimalCategory(val label: String) {
    AMFIBI("Amfibi"),
    PISCES("Pisces"),
    AVES("Aves"),
    REPTILIA("Reptilia"),
    MAMALIA("Mamalia")
}

enum class FoodType(val label: String) {
    HERBIVORA("Herbivora"),
    KARNIVORA("Karnivora"),
    OMNIVORA("Omnivora")
}

data class Animal(val name: String, val category: AnimalCategory, val foodType: FoodType)


    val animals = listOf(
        Animal("Katak", AnimalCategory.AMFIBI, FoodType.OMNIVORA),
        Animal("Ikan Mas", AnimalCategory.PISCES, FoodType.HERBIVORA),
        Animal("Burung Cendrawasih", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Buaya", AnimalCategory.REPTILIA, FoodType.KARNIVORA),
        Animal("Singa", AnimalCategory.MAMALIA, FoodType.KARNIVORA),
        Animal("Kura-kura", AnimalCategory.REPTILIA, FoodType.HERBIVORA),
        Animal("Anjing", AnimalCategory.MAMALIA, FoodType.OMNIVORA),
        Animal("Elang", AnimalCategory.AVES, FoodType.KARNIVORA),
        Animal("Ular", AnimalCategory.REPTILIA, FoodType.KARNIVORA),
        Animal("Kucing", AnimalCategory.MAMALIA, FoodType.OMNIVORA),
        Animal("Paus", AnimalCategory.PISCES, FoodType.OMNIVORA),
        Animal("Harimau", AnimalCategory.MAMALIA, FoodType.KARNIVORA),
        Animal("Kanguru", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Gajah", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Hiu", AnimalCategory.PISCES, FoodType.KARNIVORA),
        Animal("Kuda", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Singa Laut", AnimalCategory.PISCES, FoodType.KARNIVORA),
        Animal("Badak", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Burung Hantu", AnimalCategory.AVES, FoodType.KARNIVORA),
        Animal("Kodok", AnimalCategory.AMFIBI, FoodType.OMNIVORA),
        Animal("Jerapah", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Cacing", AnimalCategory.AMFIBI, FoodType.OMNIVORA),
        Animal("Kelelawar", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Landak", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Marmut", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Burung Gagak", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Belut", AnimalCategory.PISCES, FoodType.OMNIVORA),
        Animal("Kepiting", AnimalCategory.AMFIBI, FoodType.OMNIVORA),
        Animal("Serigala", AnimalCategory.MAMALIA, FoodType.KARNIVORA),
        Animal("Koala", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Komodo", AnimalCategory.REPTILIA, FoodType.KARNIVORA),
        Animal("Laba-laba", AnimalCategory.AMFIBI, FoodType.OMNIVORA),
        Animal("Pinguin", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Kura-kura Air Tawar", AnimalCategory.REPTILIA, FoodType.HERBIVORA),
        Animal("Kuda Nil", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Macan Tutul", AnimalCategory.MAMALIA, FoodType.KARNIVORA),
        Animal("Ayam", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Katak Bullfrog", AnimalCategory.AMFIBI, FoodType.OMNIVORA),
        Animal("Kucing Hutan", AnimalCategory.MAMALIA, FoodType.KARNIVORA),
        Animal("Hamster", AnimalCategory.MAMALIA, FoodType.OMNIVORA),
        Animal("Kura-kura Darat", AnimalCategory.REPTILIA, FoodType.HERBIVORA),
        Animal("Ular Kobra", AnimalCategory.REPTILIA, FoodType.KARNIVORA),
        Animal("Kuda Laut", AnimalCategory.PISCES, FoodType.HERBIVORA),
        Animal("Monyet", AnimalCategory.MAMALIA, FoodType.OMNIVORA),
        Animal("Bebek", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Anoa", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Cicak", AnimalCategory.REPTILIA, FoodType.OMNIVORA),
        Animal("Angsa", AnimalCategory.AVES, FoodType.HERBIVORA),
        Animal("Kelelawar Buah", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Tapir", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Owa", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Burung Merak", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Kupu-kupu", AnimalCategory.AMFIBI, FoodType.HERBIVORA),
        Animal("Anakonda", AnimalCategory.REPTILIA, FoodType.KARNIVORA),
        Animal("Cendrawasih", AnimalCategory.AVES, FoodType.OMNIVORA),
        Animal("Tupai", AnimalCategory.MAMALIA, FoodType.OMNIVORA),
        Animal("Pinguin Raja", AnimalCategory.AVES, FoodType.KARNIVORA),
        Animal("Bekantan", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Kadal", AnimalCategory.REPTILIA, FoodType.OMNIVORA),
        Animal("Zebra", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Gorila", AnimalCategory.MAMALIA, FoodType.OMNIVORA),
        Animal("Puma", AnimalCategory.MAMALIA, FoodType.KARNIVORA),
        Animal("Kudanil", AnimalCategory.MAMALIA, FoodType.HERBIVORA),
        Animal("Platypus", AnimalCategory.MAMALIA, FoodType.OMNIVORA)
        )



@Composable
fun AnimalApp() {
    var selectedCategory by remember { mutableStateOf(AnimalCategory.AMFIBI) }
    var selectedFoodTypes by remember { mutableStateOf(listOf(FoodType.HERBIVORA)) }
    var filteredAnimals by remember { mutableStateOf(listOf<Animal>()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selamat datang di Aplikasi Pilihan Hewan")
        Spacer(modifier = Modifier.height(16.dp))

        // Pilihan kategori hewan (Radio button)
        Row {
            Text("Pilih Kategori: ")
            AnimalCategory.values().forEach { category ->
                RadioButton(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                )
                Text(category.label)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Pilihan jenis makanan hewan (Checkbox)
        Row {
            Text("Pilih Jenis Makanan: ")
            FoodType.values().forEach { foodType ->
                Checkbox(
                    checked = selectedFoodTypes.contains(foodType),
                    onCheckedChange = {
                        selectedFoodTypes = if (it) {
                            selectedFoodTypes + foodType
                        } else {
                            selectedFoodTypes - foodType
                        }
                    },
                    modifier = Modifier.padding(end = 8.dp), // Tambahkan padding agar tampilan lebih teratur
                )
                Text(text = foodType.label)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk menampilkan hewan sesuai filter
        Button(
            onClick = {
                filteredAnimals = animals.filter { it.category == selectedCategory && it.foodType in selectedFoodTypes }
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Tampilkan Hewan Sesuai Filter")
        }

        // Tampilkan hewan-hewan yang cocok dengan pilihan
        Text("Hewan yang cocok dengan pilihan Anda:")
        filteredAnimals.forEach { animal ->
            Text(animal.name)
        }

        Text("Terima kasih telah menggunakan Aplikasi Pilihan Hewan")
    }
}
