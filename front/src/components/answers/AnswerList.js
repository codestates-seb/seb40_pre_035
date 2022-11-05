import AnswerItem from './AnswerItem';

function AnswerList({ list, updated }) {
  return (
    list && (
      <div className="answers-group">
        <h3 className="px-6 mb-3 text-2xl">{`${list.length} Answers`}</h3>
        {list.map((anItem) => (
          <AnswerItem key={anItem.id} item={anItem} setUpdate={updated} />
        ))}
      </div>
    )
  );
}

export default AnswerList;
