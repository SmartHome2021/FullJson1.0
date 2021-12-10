package com.example.fulljson10.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fulljson10.R
import com.example.fulljson10.model.Film

//Adapter отвечает за извлечение данных из набора данных
// и за создание объектов View по основе этих данных.

//1. В классе MyMovieAdapter мы создаем переменные, которые будут доступны только в этом классе (private val)
class MyMovieAdapter(
//    context - Переменная имеющая тип  Context
    /*
    Context – это объект,
    который предоставляет доступ к базовым функциям приложения:
    доступ к ресурсам, к файловой системе, вызов активности и т.д.
    Activity является подклассом Context
    */
    private val context: Context,
//    movieList - Переменная имеющая структуру нашего Data class
    private val movieList: List<Film>,
//    listener - интерфейс представляющий собой модель доступа к нашему Data Class
    private val listener: OnFilmSelectListener
)
    /*
    Указываем тип возвращаемого значения
    у нас это будет RecyclerView.Adapter типа MyMovieAdapter.MyViewHolder
    */
    : RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {

//3. Создаем класс MyViewHolder,
// в данном класс указываем параметр itemView: View, переменую listener (из за структуры нашего json)
    class MyViewHolder(itemView: View, val listener: OnFilmSelectListener) :
// и тип возвращаемого значения RecyclerView.ViewHolder и в тело данного класса мы помещаем переменные,
// привязанные к элементам XML которые мы планируем заполнять, например:
        RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.filmPoster)
        val title: TextView = itemView.findViewById(R.id.filmTitle)
        val fullTitle: TextView = itemView.findViewById(R.id.filmFullTitle)
        val rank: TextView = itemView.findViewById(R.id.filmRank)
        val year: TextView = itemView.findViewById(R.id.filmYear)
        val rating: TextView = itemView.findViewById(R.id.filmRating)
        val count: TextView = itemView.findViewById(R.id.filmCount)

//4.     Функция bind с типом даных на основе нашего Data Class служит для обработки кликов и
//    присвоений значений из переменной с методами на основе нашего Data Class.
        fun bind(listItem: Film) {
            itemView.setOnClickListener {
                listener.onSelect(listItem)
            }
            Glide.with(poster.context).load(listItem.image).into(poster)
            title.text = listItem.title
            fullTitle.text = listItem.fullTitle
            rank.text = listItem.rank.toString()
            year.text = (year.context.getString(R.string.Year) + "  " + listItem.year.toString())
            rating.text =
                (rating.context.getString(R.string.Rating) + "  " + listItem.imDbRating.toString())
            count.text =
                (count.context.getString(R.string.Count) + "  " + listItem.imDbRatingCount.toString())



        }
    }
/*
2. Имплементируем методы компонента Recicle View, а именно onCreateViewHolder, getItemCount и onBindViewHolder.
*/

//2.1 onCreateViewHolder вызывается RecyclerView для отображения данных в указанной позиции.
// Этот метод должен обновить содержимое
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return MyViewHolder(itemView, listener)
    }

//    2.2 getItemCount Возвращает общее количество элементов в наборе данных, хранящемся в адаптере.
//Возврат:
//Общее количество элементов в этом адаптере.
// Переменную movieList мы обьявили в шапке.
    override fun getItemCount() = movieList.size


// 2.3 onBindViewHolder используется если позиция элементов нам понадобится позже,
// к примеру для обработки кликов.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)
    }
}