import {
  StacksEditor,
  registerLocalizationStrings,
  EditorType,
} from '@stackoverflow/stacks-editor';
import { useEffect, useRef, useState } from 'react';
import '@stackoverflow/stacks-editor/dist/styles.css';
import '@stackoverflow/stacks';
import '@stackoverflow/stacks/dist/css/stacks.css';

console.log(StacksEditor);
console.log(registerLocalizationStrings);
console.log(EditorType);

function SoEditorContainer() {
  const editorRef = useRef();
  const [editorText, setEditorText] = useState('*Your* **markdown** here');

  const editorChangeHandle = () => {
    console.log(editorRef);
    setEditorText();
  };
  useEffect(() => {
    new StacksEditor(editorRef.current, editorText, {});
  }, []);

  return (
    <div
      className="so-Editor"
      ref={editorRef}
      onChange={(e) => editorChangeHandle(e)}
    ></div>
  );
}

export default SoEditorContainer;
